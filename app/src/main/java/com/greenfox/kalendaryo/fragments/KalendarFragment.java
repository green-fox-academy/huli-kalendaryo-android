package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.TabViewActivity;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.models.MergedCalendarResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        KalMerged kalMerged;
        ApiService apiService = RetrofitClient.getApi("backend");
        apiService.postCalendar(kalMerged).enqueue(new Callback<MergedCalendarResponse>() {
            @Override
            public void onResponse(Call<MergedCalendarResponse> call, Response<MergedCalendarResponse> response) {

            }

            @Override
            public void onFailure(Call<MergedCalendarResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });{
            @Override
            public void onResponse(Call< KalUser > call, Response<KalUser> response) {
                KalUser kalUser = response.body();
                String accessToken = kalUser.getAccessToken();
                String clientToken = kalUser.getClientToken();
                editKalPref(userEmail, userName, accessToken, clientToken);
                Log.d("shared", kalPref.getString(userEmail));
                Intent signIn = new Intent(LoginActivity.this, TabViewActivity.class);
                signIn.putExtra("googleAccountName", userEmail);
                startActivity(signIn);
            }

        };



        return inflater.inflate(R.layout.tab_fragment_1, container, false);

    }
}
