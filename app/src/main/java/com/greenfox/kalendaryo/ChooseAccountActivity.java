package com.greenfox.kalendaryo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.accounts.AccountManager.newChooseAccountIntent;

public class ChooseAccountActivity extends AppCompatActivity implements View.OnClickListener {

    Button showAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        showAccounts = findViewById(R.id.showAccountsForMerging);
    }

    private void chooseAccount() {
        // getting the accounts (KalUsers) from KalPref
        // or not
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showAccountsForMerging:
                chooseAccount();
                break;
        }
    }
}
