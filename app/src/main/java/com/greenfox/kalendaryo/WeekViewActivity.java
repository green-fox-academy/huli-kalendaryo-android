package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.event.PreviewEvent;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity extends AppCompatActivity implements
        MonthLoader.MonthChangeListener {

    private KalPref kalPref;
    private Kalendar kalendar;
    private GoogleApi googleApi;
    List<PreviewEvent> previewEvents = new ArrayList<>();
    List<WeekViewEvent> weekViewEvents = new ArrayList<>();
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    Button sendToBackend;
    long eventId = 0;
    int counter = 0;
    int numberOfColors = 4;

    WeekView mWeekView;

    @Inject
    BackendApi backendApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_week_view);

        DaggerApiComponent.builder().build().inject(this);

        Bundle bundle = getIntent().getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");

        kalPref = new KalPref(this.getApplicationContext());

        kalendar = (Kalendar) getIntent().getSerializableExtra("list");

        mWeekView = findViewById(R.id.weekview);
        mWeekView.setMonthChangeListener(this);

        sendToBackend = findViewById(R.id.send_to_backend);

        sendToBackend.setOnClickListener(v -> {
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
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        //the method runs 3 times by default, so a counter is implemented
        if (counter == 0) {

            previewEvents = (List<PreviewEvent>) getIntent().getSerializableExtra("weekViewEvents");

            if (previewEvents.size() == 0) {
                Toast.makeText(this, R.string.no_events, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.swipe_to_see, Toast.LENGTH_LONG).show();
            }

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

        defineStartTime(previewEvent, startTime);
        defineEndTime(previewEvent, endTime);

        startTime.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        endTime.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
        WeekViewEvent weekViewEvent = new WeekViewEvent(eventId, previewEvent.getSummary(), startTime, endTime);
        weekViewEvent.setColor(randomColor);
        return weekViewEvent;
    }

    public Calendar defineStartTime(PreviewEvent previewEvent, Calendar startTime) {
        if (previewEvent.getStart().getDateTime() == null) {
            String dateOfStart = previewEvent.getStart().getDate();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(dateOfStart);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            startTime.setTime(date);
        } else {
            startTime.setTime(previewEvent.getStart().getDateTime());
        }
        return startTime;
    }

    public Calendar defineEndTime(PreviewEvent previewEvent, Calendar endTime) {
        if (previewEvent.getEnd().getDateTime() == null) {
            String dateOfEnd = previewEvent.getStart().getDate();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = format.parse(dateOfEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            endTime.setTime(date);
            endTime.add(Calendar.HOUR, +2);
        } else {
            endTime.setTime(previewEvent.getEnd().getDateTime());
        }
        return endTime;
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

