package com.greenfox.kalendaryo.http;

import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Szilvi on 2017. 12. 21..
 */

public class RetrofitClient {

    static String BASE_URL_BACKEND = "http://10.27.6.127:8080";
    static String BASE_URL_GOOGLE = "https://www.googleapis.com/calendar/v3/users/me/";
    static String BASE_URL_GOOGLE_EVENT = "https://www.googleapis.com/calendar/v3/calendars/";

    public static Retrofit getConnection(String urlType) {
        return new Retrofit.Builder()
                .baseUrl(urlType)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).build())
                .build();
    }

    public static BackendApi getBackendApi() {
        return getConnection(BASE_URL_BACKEND).create(BackendApi.class);
    }

    public static GoogleApi getGoogleApi() {
        return getConnection(BASE_URL_GOOGLE).create(GoogleApi.class);
    }

    public static GoogleApi getGoogleEvents() {
        return getConnection(BASE_URL_GOOGLE_EVENT).create(GoogleApi.class);
    }
}
