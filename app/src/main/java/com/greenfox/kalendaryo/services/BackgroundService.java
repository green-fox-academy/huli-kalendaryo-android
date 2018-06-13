package com.greenfox.kalendaryo.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.alamkanak.weekview.WeekViewEvent;
import com.greenfox.kalendaryo.WeekViewActivity;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.event.EventResponse;
import com.greenfox.kalendaryo.models.event.PreviewEvent;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;

public class BackgroundService extends IntentService {

    private GoogleApi googleApi;
    private List<PreviewEvent> eventsFromGoogle = new ArrayList<>();
    private List<PreviewEvent> weekViewEvents = new ArrayList<>();
    private List<GoogleCalendar> googleCalendars = new ArrayList<>();
    private KalPref kalPref;

    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        kalPref = new KalPref(this);

        googleApi = RetrofitClient.getGoogleEvents();
        ArrayList<String> accounts = kalPref.getAccounts();

        Bundle bundle = intent.getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = googleAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;
            for (GoogleCalendar googleCalendar : googleCalendars) {
                try {
                    Response<EventResponse> result = googleApi.getEventList(authorization, googleCalendar.getId()).execute();
                    eventsFromGoogle.addAll(result.body().getItems());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (PreviewEvent event : eventsFromGoogle) {
               weekViewEvents.add(event);
            }

            Intent backgroundIntent = new Intent("weekViewEvents");
            backgroundIntent.putExtra("weekViewEvents", (Serializable) weekViewEvents);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(backgroundIntent);

        }
    }

