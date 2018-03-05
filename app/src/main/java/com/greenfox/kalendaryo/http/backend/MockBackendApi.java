package com.greenfox.kalendaryo.http.backend;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BalazsSalfay on 2018. 02. 28..
 */

public class MockBackendApi implements BackendApi {

    @Override
    public Call<KalUser> postAuth(String clientToken, KalAuth auth) {
        KalUser user = new KalUser(2, "sdsdfkj34231", "jimbo@jimbo.com", "56365ghgsg1223");
        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(this, Response.success(user));
            }
        };
        return call;
    }

    @Override
    public Call<MergedKalendarResponse> postCalendar(String clientToken, KalMerged kalMerged) {
        MergedKalendarResponse mergKalResponse = new MergedKalendarResponse("jimbo@jimbo.com","created");
        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(this, Response.success(mergKalResponse));
            }
        };
        return call;
    }
}
