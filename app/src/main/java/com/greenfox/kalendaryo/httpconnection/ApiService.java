package com.greenfox.kalendaryo.httpconnection;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public interface ApiService {

    @POST("/auth")
    Call<KalUser> getAccessToken(@Body KalAuth auth);


}
