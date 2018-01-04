package com.greenfox.kalendaryo.httpconnection;

import android.content.SharedPreferences;

import com.google.api.services.calendar.model.CalendarListEntry;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/auth")
    Call<KalUser> getAccessToken(@Body KalAuth auth);


    @GET("/calendarList")
    Call<List<CalendarListEntry>> getCalendarList(@Header("Authorization") String accessToken);
}
