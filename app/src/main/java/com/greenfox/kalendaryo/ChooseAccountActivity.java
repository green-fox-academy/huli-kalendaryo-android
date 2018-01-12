package com.greenfox.kalendaryo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.ArrayList;
import java.util.List;

public class ChooseAccountActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton radioButton;
    ListView accountNames;
    AccountAdapter adapter;
    KalPref kalpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        radioButton = findViewById(R.id.radioButton);
        adapter = new AccountAdapter(this);
        accountNames = findViewById(R.id.accountList);
        accountNames.setAdapter(adapter);
        kalpref = new KalPref(this.getApplicationContext());
    }

    public void fillAdapter() {
         // getting the accounts (KalUsers) from KalPref
         ArrayList<String> accountNameList = kalpref.getAccounts();

        for (String s: accountNameList) {
            adapter.add(s);
        }
    }

    private void chooseAccount() {
        // radiobutton

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioButton:
                fillAdapter();
                chooseAccount();
                break;
        }
    }
}
