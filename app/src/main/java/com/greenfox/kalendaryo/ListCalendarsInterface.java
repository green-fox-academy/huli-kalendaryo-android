package com.greenfox.kalendaryo;

import com.google.api.services.calendar.model.Calendar;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public interface ListCalendarsInterface {

    @GET("/users/me/calendarList/huli.opal.kalendaryo@gmial.com")
    Call<Calendar> calendars(
            @Query("access_token") String token
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/calendar/v3")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}


