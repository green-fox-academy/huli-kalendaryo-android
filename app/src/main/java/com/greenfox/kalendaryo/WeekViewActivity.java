package com.greenfox.kalendaryo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
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
import com.greenfox.kalendaryo.services.BackgroundService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity extends AppCompatActivity implements WeekView.EventClickListener,
        MonthLoader.MonthChangeListener, WeekView.EventLongPressListener,
        WeekView.EmptyViewLongPressListener {

    private KalPref kalPref;
    private Kalendar kalendar;
    private GoogleApi googleApi;
    BackendApi backendApi;
    List<PreviewEvent> previewEvents = new ArrayList<>();
    List<WeekViewEvent> weekViewEvents = new ArrayList<>();
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    Button sendToBackend;
    long eventId = 0;

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
                Intent i = new Intent(WeekViewActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
                i.putExtra("list", kalendar);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        previewEvents = (List<PreviewEvent>) getIntent().getSerializableExtra("weekViewEvents");

        System.out.println("PVE: " + previewEvents.size());
        for(PreviewEvent event: previewEvents) {
            Calendar startTime = Calendar.getInstance();
            startTime.setTime(event.getStart().getDateTime());
            Calendar endTime = (Calendar) startTime.clone();
            endTime.setTime(event.getEnd().getDateTime());
            WeekViewEvent weekViewEvent = new WeekViewEvent(eventId, event.getSummary(), startTime, endTime);
            weekViewEvents.add(weekViewEvent);
            eventId ++;
            System.out.println("START:" + startTime);
            System.out.println("END: " + endTime);
            System.out.println("NAME: " + event.getSummary());
            System.out.println("ID " + eventId);
        }


        System.out.println("WVE: " + weekViewEvents.size());

        return weekViewEvents;
    }

    /*private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }*/

    public void getEventsFromBackgroundService() {

        previewEvents = (List<PreviewEvent>) getIntent().getSerializableExtra("weekViewEvents");

        System.out.println("PVE: " + previewEvents.size());
            for(PreviewEvent event: previewEvents) {
                Calendar startTime = Calendar.getInstance();
                startTime.setTime(event.getStart().getDateTime());
                Calendar endTime = (Calendar) startTime.clone();
                endTime.setTime(event.getEnd().getDateTime());
                WeekViewEvent weekViewEvent = new WeekViewEvent(eventId, event.getSummary(), startTime, endTime);
                weekViewEvents.add(weekViewEvent);
                eventId ++;
                System.out.println("START:" + startTime);
                System.out.println("END: " + endTime);
                System.out.println("NAME: " + event.getSummary());
                System.out.println("ID " + eventId);
                }


        System.out.println("WVE: " + weekViewEvents.size());
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }
}

