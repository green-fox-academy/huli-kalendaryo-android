package com.greenfox.kalendaryo.http.backend;

import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.responses.GetKalendarListResponse;
import com.greenfox.kalendaryo.models.responses.GetKalendarResponse;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BalazsSalfay on 2018. 02. 28..
 */

public class MockBackendApi implements BackendApi {

    @Override
    public Call<KalUser> postAuth(String clientToken, GoogleAuth auth) {
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
    public Call<PostKalendarResponse> postCalendar(String clientToken, Kalendar kalendar) {
        PostKalendarResponse kalendarResponse = new PostKalendarResponse("jimbo@jimbo.com","created");
        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(this, Response.success(kalendarResponse));
            }
        };
        return call;
    }

    @Override
    public Call<GetKalendarListResponse> getCalendar(String clientToken) {
        GetKalendarListResponse listResponse = new GetKalendarListResponse();

        GetKalendarResponse kalendarResponse1 = new GetKalendarResponse("elon@musk.com", "Spurs-Opal");
        List<String> inputCalendarIds1 = new ArrayList<>(Arrays.asList("Spurs", "Opal"));
        kalendarResponse1.setInputCalendarIds(inputCalendarIds1);

        GetKalendarResponse kalendarResponse2 = new GetKalendarResponse("tung@quoc.com", "Lazio-Apple");
        List<String> inputCalendarIds2 = new ArrayList<>(Arrays.asList("Lazio", "Apple"));
        kalendarResponse2.setInputCalendarIds(inputCalendarIds2);

        listResponse.getKalendars().add(kalendarResponse1);
        listResponse.getKalendars().add(kalendarResponse2);

        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(this,Response.success(listResponse));
            }
        };
        return call;
    }
}
