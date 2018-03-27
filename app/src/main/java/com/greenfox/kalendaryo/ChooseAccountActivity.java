package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView accountNamesView;
    KalPref kalpref;
    Button next;
    KalMerged kalMerged;
    List<Kalendar> googleCalendars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kalMerged = (KalMerged) getIntent().getSerializableExtra("list");

        setContentView(R.layout.activity_choose_account);

        kalpref = new KalPref(this.getApplicationContext());
        next = findViewById(R.id.gottoweekview);

        Bundle bundle = getIntent().getExtras();
        googleCalendars = bundle.getParcelableArrayList("googeleCalendars");


        String clientToken = kalpref.clientToken();

        String[] array = new String[kalMerged.getInputCalendarIds().size()];
        for (int j = 0; j < kalMerged.getInputCalendarIds().size(); j++) {
            array[j] = kalMerged.getInputCalendarIds().get(j);
        }

        kalMerged.setInputCalendarIds(Arrays.asList(array));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseAccountActivity.this, StaticWeekViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("googeleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
                i.putExtra("list", kalMerged);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);

        accountNamesView = findViewById(R.id.accountNames);
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);

        AccountAdapter accountAdapter = new
                AccountAdapter(kalpref.getKalAuths(), this);

        accountAdapter.setEmailChange(new AccountAdapter.EmailChange() {
            @Override
            public void emailChanged(String email) {
                kalMerged.setOutputCalendarId(email);
            }
        });
        accountNamesView.setAdapter(accountAdapter);
    }


}
