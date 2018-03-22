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
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.MergedCalendarListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    KalPref kalPref;
    FloatingActionButton floatingActionButton;
    private MergedKalendarAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    BackendApi backendApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerApiComponent.builder().build().inject(this);
        View view = inflater.inflate(R.layout.kalendarlist, container, false);
        adapter = new MergedKalendarAdapter(getActivity());
        floatingActionButton = view.findViewById(R.id.choosecalendar);
        recyclerView = view.findViewById(R.id.apilistcalendars);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        kalPref = new KalPref(this.getContext());
        getCalendarResponse(kalPref.clientToken());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SelectCalendarActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void getCalendarResponse(String clientToken) {
        backendApi.getCalendar(clientToken).enqueue(new Callback<MergedCalendarListResponse>() {

            @Override
            public void onResponse(Call<MergedCalendarListResponse> call, Response<MergedCalendarListResponse> response) {
                adapter.addMergedCalendarResponse(response.body().getMergedCalendars());

            }

            @Override
            public void onFailure(Call<MergedCalendarListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
