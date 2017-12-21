package com.greenfox.kalendaryo;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/auth")
    Call<KalUser> getAccessToken(@Body KalAuth auth);
}
