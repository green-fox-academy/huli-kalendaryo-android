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

    public static Retrofit getConnection(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).build())
                .build();
    }

    public static ApiService getApi(String url) {
        return getConnection(url).create(ApiService.class);
    }
}
