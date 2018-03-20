package com.greenfox.kalendaryo.http.google;

import com.greenfox.kalendaryo.models.GoogleCalendarsResponse;
import com.greenfox.kalendaryo.models.event.EventsResource;

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
    Call<GoogleCalendarsResponse> getCalendarList(@Header("Authorization") String authorization);

    @Headers("Accept: application/json")
    @GET("{calendarId}/events/{eventId}")
    Call<EventsResource> getEvent(@Header("Authorization") String authorization, @Path("calendarId")String calendarId, @Path("eventId")String eventId);
}
