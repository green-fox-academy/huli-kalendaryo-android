package com.greenfox.kalendaryo.httpconnection;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalendarsResponse;
import com.greenfox.kalendaryo.models.KalUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/auth")
    Call<KalUser> postAuth(@Header("X-Client-Token") String clientToken, @Body KalAuth auth);

    @Headers("Accept: application/json")
    @GET("calendarList")
    Call<KalendarsResponse> getCalendarList(@Header("Authorization") String authorization);
}
