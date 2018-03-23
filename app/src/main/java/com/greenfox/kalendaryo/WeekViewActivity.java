package com.greenfox.kalendaryo;

import com.google.api.services.calendar.Calendar;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;

/**
 * Created by Lilla on 2018. 03. 14..
 */

public class WeekViewActivity {

    HttpTransport httpTransport;
    JsonFactory jsonFactory;
    Credential credentials;

    public void getEvent() throws IOException {
        Calendar service = new Calendar
                .Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName("Kalendaryo").build();
        Event event = service.events().get("primary", "eventId").execute();
    }
}
