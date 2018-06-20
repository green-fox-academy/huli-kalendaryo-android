package com.greenfox.kalendaryo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.KalPref;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private List<GoogleAuth> auths = new ArrayList<>();
    private Context context;
    private int lastSelectedPosition = 0;
    private EmailChange emailChange;
    private KalPref kalPref;
    private boolean clickable;

    @Inject
    BackendApi backendApi;

    public AccountAdapter(List<GoogleAuth> authsIn, Context ctx, boolean clickable) {
        auths = authsIn;
        context = ctx;
        kalPref = new KalPref(context);
        DaggerApiComponent.builder().build().inject(this);
        this.clickable = clickable;
    }

    public EmailChange getEmailChange() {
        return emailChange;
    }

    public void setEmailChange(EmailChange emailChange) {
        this.emailChange = emailChange;
    }

    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (clickable) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_selectable, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        }
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
                alert.setPositiveButton("Yes", (dialog, which) -> {

                    String clientToken = kalPref.clientToken();
                    String email = auths.get(position).getEmail();

                    backendApi.deleteAccount(clientToken, email).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if (response.errorBody() == null) {
                                removeAccount(position);
                                Toast.makeText(view.getContext(), "Kalendar deleted successfully", Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    Toast.makeText(view.getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(view.getContext(), "Account can not be deleted", Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.dismiss();
                });
                alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

                alert.show();
            }
        });

        if (clickable) {
            GoogleAuth auth = auths.get(position);
            holder.accountName.setText(auth.getEmail());
            holder.radioButton.setChecked(lastSelectedPosition == position);
        } else {
            GoogleAuth auth = auths.get(position);
            holder.oneAccountName.setText(auth.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return auths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView accountName;
        public TextView oneAccountName;
        public RadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            if (clickable) {
                accountName = view.findViewById(R.id.text_account_name);
                radioButton = (RadioButton) view.findViewById(R.id.button_account_select);

                radioButton.setOnClickListener(v -> {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    if (emailChange != null) {
                        emailChange.emailChanged((String) accountName.getText());
                    }
                    Toast.makeText(AccountAdapter.this.context, accountName.getText(),
                            Toast.LENGTH_LONG).show();
                });
            } else {
                oneAccountName = view.findViewById(R.id.text_account_name);
            }
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

