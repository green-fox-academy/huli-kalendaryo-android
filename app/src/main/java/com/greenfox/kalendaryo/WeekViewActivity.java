package com.greenfox.kalendaryo;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.greenfox.kalendaryo.adapter.EventAdapter;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.event.EventResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekViewActivity {

    private GoogleApi googleApi;
    private KalPref kalPref;
    private EventAdapter adapter;
    private Kalendar googleCalendar;

    public void getEventList() throws IOException {
        googleApi = RetrofitClient.getGoogleApi();
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            KalAuth kalAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = kalAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;

            //todo how to get the googleCalendar of an Auth??? calednarId = WHAT?
            String calendarId = googleCalendar.getId();

            googleApi.getEventList(authorization, calendarId).enqueue(new Callback<EventResponse>() {
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    adapter.addEvents(response.body().getItems());
                }

                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {

                }
            });
        }
    }
}
