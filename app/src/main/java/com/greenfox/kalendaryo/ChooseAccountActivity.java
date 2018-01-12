package com.greenfox.kalendaryo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.ArrayList;
import java.util.List;

public class ChooseAccountActivity extends AppCompatActivity {

    RadioButton radioButton;
    ListView accountNames;
    AccountAdapter adapter;
    KalPref kalpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        kalpref = new KalPref(this.getApplicationContext());

        // radioButton = findViewById(R.id.radioButton);

        adapter = new AccountAdapter(this);
        accountNames = findViewById(R.id.accountList);
        accountNames.setAdapter(adapter);

        fillAdapter();
    }

    public void fillAdapter() {
         // getting the account names from KalPref
         ArrayList<String> accountNameList = kalpref.getAccounts();

        for (int i = 0; i < accountNameList.size(); i++) {
            KalAuth auth = kalpref.getAuth(accountNameList.get(i));
            adapter.add(auth);
        }
    }

    private void chooseAccount() {
        // radiobutton

    }
}
