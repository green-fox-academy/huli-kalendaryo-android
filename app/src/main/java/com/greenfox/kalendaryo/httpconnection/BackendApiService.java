package com.greenfox.kalendaryo.httpconnection;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalendarsResponse;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.MergedCalendarListResponse;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;

import java.text.StringCharacterIterator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BackendApiService {

    @POST("auth")
    Call<KalUser> postAuth(@Header("X-Client-Token") String clientToken, @Body KalAuth auth);

    @Headers("Accept: application/json")
    @POST("calendar")
    Call<MergedKalendarResponse> postCalendar(@Header("X-Client-Token") String clientToken, @Body KalMerged kalMerged);

    @Headers("Accept: application/json")
    @GET("calendar")
    Call<MergedCalendarListResponse> getCalendar(@Header("X-Client-Token") String clientToken);


}
