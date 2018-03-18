package com.greenfox.kalendaryo;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.event.EventResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity {

    HttpTransport httpTransport;
    JsonFactory jsonFactory;
    Credential credentials;
    private GoogleApi googleApi;
    private KalPref kalPref;

    public void getEventList() throws IOException {
        googleApi = RetrofitClient.getGoogleApi();
        ArrayList<String> accounts = kalPref.getAccounts();

        googleApi.getEventList(authorization, calendarId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });

        Calendar service = new Calendar
                .Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("Kalendaryo").build();

        String pageToken = null;
        do {
            Events events = service.events().list("primary").setPageToken(pageToken).execute();
            List<Event> items = events.getItems();
            for (Event event : items) {
                System.out.println(event.getSummary());
            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);

    }


    public void getCalendarList() {
        googleApi = RetrofitClient.getGoogleApi();
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            KalAuth kalAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = kalAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;

            googleApi.getCalendarList(authorization).enqueue(new Callback<KalendarsResponse>() {
                @Override
                public void onResponse(Call<KalendarsResponse> call, Response<KalendarsResponse> response) {
                    adapter.addKalendars(response.body().getItems());
                }

                @Override
                public void onFailure(Call<KalendarsResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

}
