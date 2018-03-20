package com.greenfox.kalendaryo.http.backend;

import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.MergedCalendarListResponse;
import com.greenfox.kalendaryo.models.MergedCalendarResponse;
import com.greenfox.kalendaryo.models.MergedKalendarResponse;

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
    public Call<MergedKalendarResponse> postCalendar(String clientToken, Kalendar kalendar) {
        MergedKalendarResponse mergKalResponse = new MergedKalendarResponse("jimbo@jimbo.com","created");
        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(this, Response.success(mergKalResponse));
            }
        };
        return call;
    }

    @Override
    public Call<MergedCalendarListResponse> getCalendar(String clientToken) {
        MergedCalendarListResponse listResponse = new MergedCalendarListResponse();

        MergedCalendarResponse mergedCalResponse1 = new MergedCalendarResponse("elon@musk.com", "Spurs-Opal");
        List<String> inputCalendarIds1 = new ArrayList<>(Arrays.asList("Spurs", "Opal"));
        mergedCalResponse1.setInputCalendarIds(inputCalendarIds1);

        MergedCalendarResponse mergedCalResponse2 = new MergedCalendarResponse("tung@quoc.com", "Lazio-Apple");
        List<String> inputCalendarIds2 = new ArrayList<>(Arrays.asList("Lazio", "Apple"));
        mergedCalResponse2.setInputCalendarIds(inputCalendarIds2);

        listResponse.getMergedCalendars().add(mergedCalResponse1);
        listResponse.getMergedCalendars().add(mergedCalResponse2);

        ImplCall call = new ImplCall() {
            @Override
            public void enqueue(Callback callback) {
                callback.onResponse(this,Response.success(listResponse));
            }
        };
        return call;
    }
}
