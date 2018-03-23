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
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;
import com.greenfox.kalendaryo.models.event.EventResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity extends BaseActivity implements Callback<List<Event>>  {

    private KalPref kalPref;
    private KalMerged kalMerged;
    private GoogleApi googleApi;
    private BackendApi backendApi;
    List<WeekViewEvent> eventsFromGoogle = new ArrayList<>();

    Button sendToBackend;

    public WeekViewActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.week_view_base);

        sendToBackend = findViewById(R.id.sendtobackend);

        kalPref = new KalPref(this.getApplicationContext());

        kalMerged = (KalMerged) getIntent().getSerializableExtra("list");

        getEventList();

        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendApi = RetrofitClient.getBackendApi();
                backendApi.postCalendar(getClientToken(), kalMerged).enqueue(new Callback<MergedKalendarResponse>() {
                    @Override
                    public void onResponse(Call<MergedKalendarResponse> call, Response<MergedKalendarResponse> response) {
                        response.body();
                    }

                    @Override
                    public void onFailure(Call<MergedKalendarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Intent i = new Intent(WeekViewActivity.this, MainActivity.class);
                i.putExtra("list", kalMerged);
                startActivity(i);
            }
        });
    }

    public void getEventList() {
        googleApi = RetrofitClient.getGoogleEvents();
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            KalAuth kalAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = kalAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;
            for (String calendarId : kalMerged.getInputCalendarIds()) {

                googleApi.getEventList(authorization,calendarId).enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                        eventsFromGoogle.addAll(response.body().getItems());
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
        return null;
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
