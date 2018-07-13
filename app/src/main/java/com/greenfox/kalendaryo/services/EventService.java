package com.greenfox.kalendaryo.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.event.EventResponse;
import com.greenfox.kalendaryo.models.event.PreviewEvent;
import com.greenfox.kalendaryo.models.responses.GetAccountResponse;
import com.greenfox.kalendaryo.models.responses.GetEventResponse;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Callback;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class EventService extends IntentService {

    private List<PreviewEvent> eventsFromGoogle = new ArrayList<>();
    private List<PreviewEvent> weekViewEvents = new ArrayList<>();
    private List<GoogleCalendar> googleCalendars = new ArrayList<>();
    private List<String> authorizations = new ArrayList<>();
    private KalPref kalPref;

    @Inject
    @Named("getGoogleEvents")
    GoogleApi googleApi;

    @Inject
    BackendApi backendApi;

    public EventService() {
        super("EventService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        kalPref = new KalPref(this);

        DaggerApiComponent.builder().build().inject(this);

        ArrayList<String> accounts = kalPref.getAccounts();

        Bundle bundle = intent.getExtras();
        googleCalendars = bundle.getParcelableArrayList("googleCalendars");

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));
            String accessToken = googleAuth.getAccessToken();
            authorizations.add("Bearer " + accessToken);
        }


        /*Calendar beginning = Calendar.getInstance();
        Calendar end = (Calendar) beginning.clone();
        beginning.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        beginning.set(Calendar.MONTH, 0);
        beginning.set(Calendar.DAY_OF_MONTH, 1);
        Date start = beginning.getTime();

        end.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        end.set(Calendar.MONTH, 11);
        end.set(Calendar.DAY_OF_MONTH, 31);
        Date finish = end.getTime();

        DateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String firstDayOfCurrentYear = startDateFormat.format(start);
        String lastDayOfCurrentYear = startDateFormat.format(finish);*  */


        for (GoogleCalendar googleCalendar : googleCalendars) {
            backendApi.getEvents(kalPref.clientToken(), googleCalendar).enqueue(new retrofit2.Callback<GetEventResponse>() {
                @Override
                public void onResponse(Call<GetEventResponse> call, Response<GetEventResponse> response) {
                    eventsFromGoogle.addAll(response.body().getEvents());
                }

                @Override
                public void onFailure(Call<GetEventResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            };



        for (PreviewEvent event : eventsFromGoogle) {
            weekViewEvents.add(event);
        }

        Intent backgroundIntent = new Intent("weekViewEvents");
        backgroundIntent.putExtra("weekViewEvents", (Serializable) weekViewEvents);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(backgroundIntent);

    }
}
