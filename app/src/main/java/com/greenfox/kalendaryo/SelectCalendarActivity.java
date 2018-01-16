package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;

import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalendarsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.greenfox.kalendaryo.models.Kalendar;

public class SelectCalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private ApiService apiService;
    private KalPref kalPref;
    private KalendarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);
        adapter = new KalendarAdapter(this);
        kalPref = new KalPref(this.getApplicationContext());
        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);
        getCalendarList();


    }

    public void getCalendarList() {
        apiService = RetrofitClient.getApi("google API");

        ArrayList<String> accounts = kalPref.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            KalAuth kalAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = kalAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;


            apiService.getCalendarList(authorization).enqueue(new Callback<KalendarsResponse>() {
                @Override
                public void onResponse(Call<KalendarsResponse> call, Response<KalendarsResponse> response) {
                    adapter.addAll(response.body().getItems());
                }

                @Override
                public void onFailure(Call<KalendarsResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
