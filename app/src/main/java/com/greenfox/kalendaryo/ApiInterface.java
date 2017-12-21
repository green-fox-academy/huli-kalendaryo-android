package com.greenfox.kalendaryo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public interface ApiInterface {

    @POST("/auth")
    @FormUrlEncoded
    Call<KalUser> sendAuthCode(@Body KalAuth auth);

    @GET("/auth")
    Call<List<com.google.api.services.calendar.model.Calendar>> getCalendarList(String accessToken);
}
