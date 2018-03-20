package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.GoogleCalendarAdapter;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.GoogleCalendarsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectCalendarActivity extends AppCompatActivity {

    private GoogleApi googleApi;
    private KalPref kalPref;
    private GoogleCalendarAdapter adapter;
    Button goToChooseAccount;
    Kalendar kalendar;
    RecyclerView recKal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);

        adapter = new GoogleCalendarAdapter(this);
        kalPref = new KalPref(this.getApplicationContext());
        kalendar = new Kalendar();
        getCalendarList();
        adapter.setListChange(kalendar);
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
                Intent i = new Intent(SelectCalendarActivity.this, ChooseAccountActivity.class);
                i.putExtra("list", kalendar);
                startActivity(i);
            }
        });
    }

    public void getCalendarList() {
        googleApi = RetrofitClient.getGoogleApi();
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = googleAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;

            googleApi.getCalendarList(authorization).enqueue(new Callback<GoogleCalendarsResponse>() {
                @Override
                public void onResponse(Call<GoogleCalendarsResponse> call, Response<GoogleCalendarsResponse> response) {
                    adapter.addGoogleCalendars(response.body().getItems());
                }

                @Override
                public void onFailure(Call<GoogleCalendarsResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
