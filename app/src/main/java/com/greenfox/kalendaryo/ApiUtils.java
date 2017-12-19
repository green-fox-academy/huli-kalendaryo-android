package com.greenfox.kalendaryo;

/**
 * Created by lica on 2017. 12. 19..
 */

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://localhost:8080/";
    public static ApiInterface getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
