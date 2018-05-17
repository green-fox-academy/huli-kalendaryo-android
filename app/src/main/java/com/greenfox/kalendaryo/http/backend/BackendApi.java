package com.greenfox.kalendaryo.http.backend;

import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.responses.GetKalendarListResponse;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;
import com.greenfox.kalendaryo.models.responses.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BackendApi {

    @POST("auth")
    Call<KalUser> postAuth(@Header("X-Client-Token") String clientToken, @Body GoogleAuth auth);

    @GET("auth")
    Call<AuthResponse> getAuth(@Header("X-Client-Token") String clientToken);

    @Headers("Accept: application/json")
    @POST("calendar")
    Call<PostKalendarResponse> postCalendar(@Header("X-Client-Token") String clientToken, @Body Kalendar kalendar);

    @Headers("Accept: application/json")
    @GET("calendar")
    Call<GetKalendarListResponse> getCalendar(@Header("X-Client-Token") String clientToken);

}
