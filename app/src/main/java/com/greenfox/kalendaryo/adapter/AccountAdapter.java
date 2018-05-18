package com.greenfox.kalendaryo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.AuthResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by barba on 2018. 01. 08..
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private List<GoogleAuth> auths;
    private Context context;
    private int lastSelectedPosition = -1;
    private EmailChange emailChange;
    private KalPref kalPref;

    @Inject
    BackendApi backendApi;

    public AccountAdapter(List<GoogleAuth> authsIn, Context ctx) {
        auths = authsIn;
        context = ctx;
        kalPref = new KalPref(context);
        DaggerApiComponent.builder().build().inject(this);
    }

    public EmailChange getEmailChange() {
        return emailChange;
    }

    public void setEmailChange(EmailChange emailChange) {
        this.emailChange = emailChange;
    }

    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_name_with_button, parent, false);
        AccountAdapter.ViewHolder viewHolder = new AccountAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AccountAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Warning!");
                alert.setMessage("Are you sure to delete account?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String clientToken = kalPref.clientToken();
                        String email = auths.get(position).getEmail();

                        backendApi.deleteAccount(clientToken, email).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                removeAccount(position);
                                Toast.makeText(view.getContext(), "Kalendar deleted successfully", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(view.getContext(),"Account can not be deleted", Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });
        GoogleAuth auth = auths.get(position);
        holder.accountName.setText(auth.getEmail());
        holder.radioButton.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return auths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView accountName;
        public RadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            accountName = view.findViewById(R.id.accountname);
            radioButton = view.findViewById(R.id.radioButton);
            radioButton.setOnClickListener(v -> {
                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();

                if (emailChange != null) {
                   emailChange.emailChanged((String) accountName.getText());
                }

                Toast.makeText(AccountAdapter.this.context, accountName.getText(),
                        Toast.LENGTH_LONG).show();
            });
        }
    }

    public interface EmailChange {
        void emailChanged(String email);
    }

    public void removeAccount(int position) {
        auths.remove(position);
        notifyItemRemoved(position);
    }
}
