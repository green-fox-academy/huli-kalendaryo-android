package com.greenfox.kalendaryo;

/**
 * Created by lica on 2017. 12. 19..
 */

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://kalendaryo-staging.greenfox.academy/";
    public static ApiInterface getApiInterface() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
