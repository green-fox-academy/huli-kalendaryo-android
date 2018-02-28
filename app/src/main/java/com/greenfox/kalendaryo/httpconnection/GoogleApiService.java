package com.greenfox.kalendaryo.httpconnection;

import com.greenfox.kalendaryo.models.KalendarsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by BalazsSalfay on 2018. 02. 28..
 */

public interface GoogleApiService {

    @Headers("Accept: application/json")
    @GET("calendarList")
    Call<KalendarsResponse> getCalendarList(@Header("Authorization") String authorization);
}
