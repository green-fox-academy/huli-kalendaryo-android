package com.greenfox.kalendaryo.providers;

import com.greenfox.kalendaryo.BuildConfig;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by a- on 20/03/2018.
 */

@Module
public class ApiProvider {

    static String BASE_URL_BACKEND = BuildConfig.LOCAL_IP_ADDRESS;
    static String BASE_URL_GOOGLE = "https://www.googleapis.com/calendar/v3/users/me/";

/*    @Provides
    BackendApi provideBackEndApi() {
        return RetrofitClient.getBackendApi();
    }

    @Provides
    GoogleApi provideGoogleApi() {
        return RetrofitClient.getGoogleApi();
    }*/

    @Provides
    RetrofitClient provideRetrofitClient() {
        return new RetrofitClient();
    }
}
