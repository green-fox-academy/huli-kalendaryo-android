package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.adapter.MergedKalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.BackendApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.MergedCalendarListResponse;
import com.greenfox.kalendaryo.models.MergedCalendarResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    KalPref kalpref;
    FloatingActionButton floatingActionButton;
    private MergedKalendarAdapter adapter;
    private RecyclerView recyclerView;
    private BackendApiService backendApiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kalendarlist, container, false);
        adapter = new MergedKalendarAdapter(view.getContext());
        floatingActionButton = view.findViewById(R.id.choosecalendar);
        recyclerView = view.findViewById(R.id.apilistcalendars);
        KalPref kalPref = new KalPref(this.getContext());
        String clientToken = kalPref.clientToken();
        getCalendarResponse(clientToken);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SelectCalendarActivity.class);
                startActivity(i);
            }
        });

        kalpref = new KalPref(getActivity());
        return view;

    }

    private void getCalendarResponse(String clientToken) {
        backendApiService = RetrofitClient.getBackendApi("backend");
        backendApiService.getCalendar(clientToken).enqueue(new Callback<MergedCalendarListResponse>() {

            @Override
            public void onResponse(Call<MergedCalendarListResponse> call, Response<MergedCalendarListResponse> response) {
                adapter.addMergedCalendarResponse(response.body().getMergedCalendars());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MergedCalendarListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}