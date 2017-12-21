package com.greenfox.kalendaryo.httpconn;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.greenfox.kalendaryo.ApiInterface;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Szilvi on 2017. 12. 21..
 */

public class RetrofitClient {

    public void getConnectionWithBackend(GoogleSignInAccount account) {
        ApiInterface service = new Retrofit.Builder()
                .baseUrl("http://kalendaryo-staging.greenfox.academy/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS).build())
                .build().create(ApiInterface.class);
        Log.d("dasd","sd" + account.getServerAuthCode());
        service.getAccessToken(new KalAuth(account.getServerAuthCode(), account.getDisplayName(), account.getEmail())).enqueue(new Callback<KalUser>() {
            @Override
            public void onResponse(Call<KalUser> call, Response<KalUser> response) {
                Log.d("very sorry", "access_token: " + response.body().accessToken);
            }

            @Override
            public void onFailure(Call<KalUser> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
