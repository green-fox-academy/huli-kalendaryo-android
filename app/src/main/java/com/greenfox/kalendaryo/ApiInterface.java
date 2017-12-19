package com.greenfox.kalendaryo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public interface ApiInterface {

    @POST("auth")
    Call<KalUser> getAccessToken(@Body KalAuth auth);


}
