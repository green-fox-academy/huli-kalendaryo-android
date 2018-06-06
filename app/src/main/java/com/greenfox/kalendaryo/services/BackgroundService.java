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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;

public class BackgroundService extends IntentService {

    private GoogleApi googleApi;
    private List<WeekViewEvent> eventsFromGoogle = new ArrayList<>();
    private List<WeekViewEvent> weekViewEvents = new ArrayList<>();
    private List<GoogleCalendar> googleCalendars = new ArrayList<>();
    private KalPref kalPref;

    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("THIS IS BACKGROUNDSERVICE!!!!");
        kalPref = new KalPref(this);

        googleApi = RetrofitClient.getGoogleEvents();
        ArrayList<String> accounts = kalPref.getAccounts();

        System.out.println("SIZE: " + accounts.size());

        Bundle bundle = intent.getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");
        System.out.println("GOOGLECALENDARSSIZE: " + googleCalendars.size());

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = googleAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;
            for (GoogleCalendar googleCalendar : googleCalendars) {
                try {
                    Response<EventResponse> result = googleApi.getEventList(authorization, googleCalendar.getId()).execute();
                    eventsFromGoogle.addAll(result.body().getItems());
                    System.out.println("SUCCCCCCESSSSSS!!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("FAILURE!!!!!");
                }
            }
        }

        WeekViewEvent myWeek = new WeekViewEvent().setStartTime(Calendar.YEAR);

        System.out.println("EVENTFROMGOOGLE!!!!!!!" + eventsFromGoogle.size());
        for (WeekViewEvent event : eventsFromGoogle) {
            System.out.println(event.getName());
            //if(event.getName().equals("Hello")) {
              //  weekViewEvents.add(event);

        }

        Intent intent1 = new Intent("weekViewEvents");
        intent1.putExtra("weekViewEvents", (Serializable) weekViewEvents);
        LocalBroadcastManager.getInstance(BackgroundService.this).sendBroadcast(intent1);

        System.out.println("WEEKVIEWEVENTS: " + weekViewEvents.size());

    }}
