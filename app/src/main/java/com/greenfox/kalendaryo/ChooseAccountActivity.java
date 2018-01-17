package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView accountNamesView;
    KalPref kalpref;
    Button sendToBackend;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        kalpref = new KalPref(this.getApplicationContext());
        sendToBackend = findViewById(R.id.sendtobackend);
        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.postCalendar(new KalMerged()).enqueue(new Callback<Kalendar>() {
                    @Override
                    public void onResponse(Call<Kalendar> call, Response<Kalendar> response) {
                        Kalendar kalendar = response.body();
                        
                    }

                    @Override
                    public void onFailure(Call<Kalendar> call, Throwable t) {

                    }
                });

                Intent i = new Intent(ChooseAccountActivity.this, TabViewActivity.class);
                startActivity(i);

            }
        });


        accountNamesView = findViewById(R.id.accountNames);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);
        AccountAdapter accountAdapter = new
                AccountAdapter(kalpref.getKalAuths(),this);
        accountNamesView.setAdapter(accountAdapter);
    }
}
