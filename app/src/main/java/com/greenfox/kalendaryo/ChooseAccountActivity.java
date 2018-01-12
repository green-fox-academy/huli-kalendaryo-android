package com.greenfox.kalendaryo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;

import java.util.ArrayList;
import java.util.List;

public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView accountNamesView;
    KalPref kalpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        kalpref = new KalPref(this.getApplicationContext());
        accountNamesView = findViewById(R.id.accountNames);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);
        AccountAdapter accountAdapter = new
                AccountAdapter(getAccounts(),this);
        accountNamesView.setAdapter(accountAdapter);
    }

    public List<KalAuth> getAccounts() {
         ArrayList<String> accountNameList = kalpref.getAccounts();
         ArrayList<KalAuth> auths = new ArrayList<>();

         for (int i = 0; i < accountNameList.size(); i++) {
            KalAuth auth = kalpref.getAuth(accountNameList.get(i));
            auths.add(auth);
        }
        return auths;
    }
}
