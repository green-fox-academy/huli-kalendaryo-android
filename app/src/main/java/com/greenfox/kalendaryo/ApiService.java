package com.greenfox.kalendaryo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lica on 2017. 12. 17..
 */

public interface ApiService {
    @POST("/saveuser")
    @FormUrlEncoded
    Call<UserData> savePost(@Field("email") String email, @Field("accessToken") String accessToken);
}
