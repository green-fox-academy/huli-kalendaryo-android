package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.event.PreviewEvent;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity extends AppCompatActivity implements
        MonthLoader.MonthChangeListener {

    private KalPref kalPref;
    private Kalendar kalendar;
    private GoogleApi googleApi;
    BackendApi backendApi;
    List<PreviewEvent> previewEvents = new ArrayList<>();
    List<WeekViewEvent> weekViewEvents = new ArrayList<>();
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    Button sendToBackend;
    long eventId = 0;
    int counter = 0;
    int numberOfColors = 4;

    WeekView mWeekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_week_view);

        Bundle bundle = getIntent().getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");

        kalPref = new KalPref(this.getApplicationContext());

        kalendar = (Kalendar) getIntent().getSerializableExtra("list");

        mWeekView = findViewById(R.id.weekview);
        mWeekView.setMonthChangeListener(this);

        sendToBackend = findViewById(R.id.send_to_backend);

        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendApi = RetrofitClient.getBackendApi();
                backendApi.postCalendar(kalPref.clientToken(), kalendar).enqueue(new Callback<PostKalendarResponse>() {
                    @Override
                    public void onResponse(Call<PostKalendarResponse> call, Response<PostKalendarResponse> response) {
                        PostKalendarResponse postKalendarResponse = response.body();
                    }

                    @Override
                    public void onFailure(Call<PostKalendarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(WeekViewActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
                        i.putExtra("list", kalendar);
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();
                    }
                }, 500);
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        //the method runs 3 times by default, so a counter is implemented
        if (counter == 0) {

            previewEvents = (List<PreviewEvent>) getIntent().getSerializableExtra("weekViewEvents");

            for (PreviewEvent event : previewEvents) {
                WeekViewEvent weekViewEvent = weekViewEventConverter(event);
                weekViewEvents.add(weekViewEvent);
                eventId++;
            }
            counter ++;
        } else {
            weekViewEvents = new ArrayList<>();
        }

        return weekViewEvents;
    }

    public WeekViewEvent weekViewEventConverter(PreviewEvent previewEvent) {
        int randomColorNumber = (int)(Math.random()*numberOfColors + 1);
        int randomColor = getResources().getColor(randomColorGenerator(randomColorNumber));
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = (Calendar) startTime.clone();

        startTime.setTime(previewEvent.getStart().getDateTime());
        startTime.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        endTime.setTime(previewEvent.getEnd().getDateTime());
        endTime.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        WeekViewEvent weekViewEvent = new WeekViewEvent(eventId, previewEvent.getSummary(), startTime, endTime);
        weekViewEvent.setColor(randomColor);
        return weekViewEvent;
    }

    public int randomColorGenerator(int random) {
        int colorNumber = 0;
        switch (random) {
            case 1: colorNumber = R.color.event_color_01;
            break;
            case 2: colorNumber = R.color.event_color_02;
            break;
            case 3: colorNumber = R.color.event_color_03;
            break;
            case 4: colorNumber = R.color.event_color_04;
            break;
        }
        return colorNumber;
    }

}

