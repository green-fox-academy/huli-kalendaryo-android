package com.greenfox.kalendaryo.http.backend;

import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.responses.GetKalendarListResponse;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;
import com.greenfox.kalendaryo.models.responses.GetAccountResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BackendApi {

    @POST("auth")
    Call<KalUser> postAuth(@Header("X-Client-Token") String clientToken, @Body GoogleAuth auth);

    @GET("account")
    Call<GetAccountResponse> getAccount(@Header("X-Client-Token") String clientToken);

    @DELETE("account")
    Call<Void> deleteAccount(@Header("X-Client-Token") String clientToken, @Header("email") String email);

    @Headers("Accept: application/json")
    @POST("calendar")
    Call<PostKalendarResponse> postCalendar(@Header("X-Client-Token") String clientToken, @Body Kalendar kalendar);

    @Headers("Accept: application/json")
    @GET("calendar")
    Call<GetKalendarListResponse> getCalendar(@Header("X-Client-Token") String clientToken);

    @DELETE("calendar/{id}")
    Call<Void> deleteKalendar(@Header("X-Client-Token") String clientToken, @Path("id") long id);

}
