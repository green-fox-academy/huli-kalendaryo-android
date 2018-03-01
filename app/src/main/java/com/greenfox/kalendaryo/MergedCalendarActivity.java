package com.greenfox.kalendaryo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.greenfox.kalendaryo.adapter.MergedKalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.BackendApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.MergedCalendarListResponse;
import com.greenfox.kalendaryo.models.MergedCalendarResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tung on 2/28/18.
 */

public class MergedCalendarActivity extends AppCompatActivity {
    private MergedKalendarAdapter adapter;
    private ListView listView;
    private BackendApiService backendApiService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mergedcalendarsview);
        listView = findViewById(R.id.mergedcallist);
        KalPref kalPref = new KalPref(this.getApplicationContext());
        String clientToken = kalPref.clientToken();
        backendApiService = RetrofitClient.getBackendApi("backend");
        backendApiService.getCalendar(clientToken).enqueue(new Callback<MergedCalendarListResponse>() {
            @Override
            public void onResponse(Call<MergedCalendarListResponse> call, Response<MergedCalendarListResponse> response) {
                MergedCalendarListResponse mergedCalendarListResponse = response.body();
                adapter.addAll(mergedCalendarListResponse.getMergedCalendars());

            }

            @Override
            public void onFailure(Call<MergedCalendarListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
