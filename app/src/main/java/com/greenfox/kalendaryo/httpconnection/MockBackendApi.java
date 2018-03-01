package com.greenfox.kalendaryo.httpconnection;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;


import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BalazsSalfay on 2018. 02. 28..
 */

public class MockBackendApi implements BackendApiService {

    @Override
    public Call<KalUser> postAuth(String clientToken, KalAuth auth) {
        KalUser user = new KalUser(2, "sdsdfkj34231", "jimbo@jimbo.com", "56365ghgsg1223");
        Response response = null;
        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse((Call) user, response); {
                    response.isSuccessful();
                }
            }
        };
        return (Call<KalUser>) user;
    }

    @Override
    public Call<MergedKalendarResponse> postCalendar(String clientToken, KalMerged kalMerged) {
        return null;
    }
}
