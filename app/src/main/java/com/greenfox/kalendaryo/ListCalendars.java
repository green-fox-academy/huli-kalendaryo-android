package com.greenfox.kalendaryo;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import com.google.api.services.calendar.model.Calendar;

import java.io.IOException;

import retrofit2.Call;

public class ListCalendars extends AppCompatActivity {

    ListView listof = (ListView) findViewById(R.id.apilistcalendars);

    public String getCalendars() throws IOException {
        String accessToken = System.getenv("141350348735-cibla76rafmvq6c6enon40kc6eg3r9su.apps.googleusercontent.com");
        ListCalendarsInterface listCalendarsInterface = ListCalendarsInterface.retrofit.create(ListCalendarsInterface.class);

        Call<com.google.api.services.calendar.model.Calendar> call = listCalendarsInterface.calendars(accessToken);
        Calendar content = call.execute().body();
        return (content.toString());
    }

}






