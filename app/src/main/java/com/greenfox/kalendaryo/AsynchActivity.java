package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alamkanak.weekview.WeekViewEvent;
import com.greenfox.kalendaryo.adapter.EventAdapter;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;
import com.greenfox.kalendaryo.models.event.GoogleEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsynchActivity extends BaseActivity /**implements Callback<List<GoogleEvent>> */ {


    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;
    private RetrofitClient retrofit;
    private KalPref kalPref;
    private KalMerged kalMerged;
    private GoogleApi googleApi;
    private BackendApi backendApi;
    private EventAdapter adapter;
    private GoogleEvent event;

    Button sendToBackend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        kalMerged = (KalMerged) getIntent().getSerializableExtra("list");

        setContentView(R.layout.activity_choose_account);
        kalPref = new KalPref(this.getApplicationContext());
        sendToBackend = findViewById(R.id.sendtobackend);
        backendApi = RetrofitClient.getBackendApi();

        String clientToken = kalPref.clientToken();

        String[] array = new String[kalMerged.getInputCalendarIds().size()];
        for (int j = 0; j < kalMerged.getInputCalendarIds().size(); j++) {
            array[j] = kalMerged.getInputCalendarIds().get(j);
        }
        kalMerged.setInputCalendarIds(Arrays.asList(array));
        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendApi.postCalendar(clientToken, kalMerged).enqueue(new Callback<MergedKalendarResponse>() {
                    @Override
                    public void onResponse(Call<MergedKalendarResponse> call, Response<MergedKalendarResponse> response) {
                        response.body();
                    }

                    @Override
                    public void onFailure(Call<MergedKalendarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Intent i = new Intent(AsynchActivity.this, MainActivity.class);
                i.putExtra("list", kalMerged);
                startActivity(i);
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Download events from network if it hasn't been done already. To understand how events are
        // downloaded using retrofit, visit http://square.github.io/retrofit
        if (!calledNetwork) {
            googleApi = RetrofitClient.getGoogleEvents();
            //googleApi.getEventList();
            calledNetwork = true;
        }

        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }
}
