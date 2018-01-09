package com.greenfox.kalendaryo.httpconnection;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Szilvi on 2017. 12. 21..
 */

public class RetrofitClient {

    private static OkHttpClient client;
    static String BASE_URL_BACKEND = "http://10.0.2.2:8080/";
    static String BASE_URL_GOOGLE = "https://www.googleapis.com/calendar/v3/users/me/";

    public static Retrofit getConnection(String urlType) {
        return new Retrofit.Builder()
                .baseUrl(urlType)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).build())
                .build();
    }

    public static ApiService getApi(String urlType) {
        if (urlType.equals("backend")) {
            return getConnection(BASE_URL_BACKEND).create(ApiService.class);
        } else if (urlType.equals("google API")) {
            return getConnection(BASE_URL_GOOGLE).create(ApiService.class);
        } else {
            return null;
        }
    }
}
