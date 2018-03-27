package com.greenfox.kalendaryo;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.api.services.calendar.model.Event;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.event.EventResponse;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity extends BaseActivity implements Callback<List<Event>>  {

    private KalPref kalPref;
    private GoogleApi googleApi;
    private BackendApi backendApi;
    Kalendar kalendar;
    List<WeekViewEvent> eventsFromGoogle = new ArrayList<>();
    List<WeekViewEvent> weekViewEvents = new ArrayList<>();
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    Button sendToBackend;

    public WeekViewActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_view_base);
        sendToBackend = findViewById(R.id.sendtobackend);
        kalPref = new KalPref(this.getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");

        getEventList();

        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendApi = RetrofitClient.getBackendApi();
                backendApi.postCalendar(getClientToken(), kalendar).enqueue(new Callback<PostKalendarResponse>() {
                    @Override
                    public void onResponse(Call<PostKalendarResponse> call, Response<PostKalendarResponse> response) {
                        response.body();
                    }

                    @Override
                    public void onFailure(Call<PostKalendarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Intent i = new Intent(WeekViewActivity.this, MainActivity.class);
                i.putExtra("list", kalendar);
                startActivity(i);
            }
        });
    }

    public void getEventList() {
        googleApi = RetrofitClient.getGoogleEvents();
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth auth = kalPref.getAuth(accounts.get(i));

            String accessToken = auth.getAccessToken();
            String authorization = "Bearer " + accessToken;
            for (GoogleCalendar googleCalendar : googleCalendars) {

                googleApi.getEventList(authorization,googleCalendar.getId()).enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                        //eventsFromGoogle.addAll(response.body().getItems());
                    }

                    @Override
                    public void onFailure(Call<EventResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }
    }

    public String getClientToken() {
        return kalPref.clientToken();
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        for (WeekViewEvent event : eventsFromGoogle) {
            if (eventMatches(event, newYear, newMonth)) {
                weekViewEvents.add(event);
            }
        }
        return weekViewEvents;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
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

    @Override
    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

    }

    @Override
    public void onFailure(Call<List<Event>> call, Throwable t) {

    }
}
