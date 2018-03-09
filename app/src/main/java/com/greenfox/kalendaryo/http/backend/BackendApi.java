package com.greenfox.kalendaryo.http.backend;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.MergedCalendarListResponse;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;

import java.text.StringCharacterIterator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BackendApi {

    @POST("auth")
    Call<KalUser> postAuth(@Header("X-Client-Token") String clientToken, @Body KalAuth auth);

    @Headers("Accept: application/json")
    @POST("calendar")
    Call<MergedKalendarResponse> postCalendar(@Header("X-Client-Token") String clientToken, @Body KalMerged kalMerged);

    @Headers("Accept: application/json")
    @GET("calendar")
    Call<MergedCalendarListResponse> getCalendar(@Header("X-Client-Token") String clientToken);

}
