package com.greenfox.kalendaryo.http.google;

import android.util.EventLog;

import com.google.api.services.calendar.model.Event;
import com.greenfox.kalendaryo.models.KalendarsResponse;
import com.greenfox.kalendaryo.models.event.EventsResource;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by BalazsSalfay on 2018. 02. 28..
 */

public interface GoogleApi {

    @Headers("Accept: application/json")
    @GET("calendarList")
    Call<KalendarsResponse> getCalendarList(@Header("Authorization") String authorization);

    @Headers("Accept: application/json")
    @GET("{calendarId}/events/{eventId}")
    Call<EventsResource> getEvents(@Header("Authorization") String authorization, @Path("calendarId")String calendarId, @Path("eventId")String eventId);
}
