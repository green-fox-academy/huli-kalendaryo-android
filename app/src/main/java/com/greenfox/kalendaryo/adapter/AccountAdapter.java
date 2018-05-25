package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.GoogleAuth;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private List<GoogleAuth> auths = new ArrayList<>();
    private Context context;
    private int lastSelectedPosition = -1;
    private EmailChange emailChange;
    private boolean clickable;


    public AccountAdapter(List<GoogleAuth> authsIn, Context ctx, boolean clickable) {
        auths = authsIn;
        context = ctx;
        this.clickable = clickable;
    }

    public AccountAdapter(Context context, boolean clickable) {
        this.context = context;
        this.clickable = clickable;
    }

    public EmailChange getEmailChange() {
        return emailChange;
    }

    public void setEmailChange(EmailChange emailChange) {
        this.emailChange = emailChange;
    }

    public void addAll(List<GoogleAuth> auths) {
        this.auths = auths;
        notifyDataSetChanged();
    }

    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (clickable) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_name_with_button, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oneaccount, parent, false);
        }
        AccountAdapter.ViewHolder viewHolder = new AccountAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AccountAdapter.ViewHolder holder, int position) {
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
                accountName = view.findViewById(R.id.accountName);
                radioButton = (RadioButton) view.findViewById(R.id.radioButton);
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
                oneAccountName = view.findViewById(R.id.oneAccountName);
            }
        }
    }

    public interface EmailChange {
        void emailChanged(String email);
    }
}