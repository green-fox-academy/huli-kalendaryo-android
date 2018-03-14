package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.adapter.SharingOptionsAdapter;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.KalendarsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SharingOptionsActivity extends AppCompatActivity {

    private GoogleApi googleApi;
    private KalPref kalPref;
    private SharingOptionsAdapter adapter;
    Button goToChooseAccount;
    KalMerged kalMerged;
    RecyclerView recKal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_options);

        kalMerged = (KalMerged) getIntent().getSerializableExtra("list");

        adapter = new SharingOptionsAdapter(this);
        adapter.setListChange(kalMerged);
        recKal = findViewById(R.id.listView);
        recKal.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recKal.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recKal.getContext(),
                        recyclerLayoutManager.getOrientation());
        recKal.addItemDecoration(dividerItemDecoration);
        goToChooseAccount = findViewById(R.id.gotochooseaccount);
        goToChooseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SharingOptionsActivity.this, ChooseAccountActivity.class);
                i.putExtra("list", kalMerged);
                startActivity(i);
            }
        });
    }
}
