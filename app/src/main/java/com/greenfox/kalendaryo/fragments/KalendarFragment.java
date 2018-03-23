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
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetKalendarListResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    KalPref kalPref;
    FloatingActionButton floatingActionButton;
    private KalendarAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    BackendApi backendApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kalendarlist, container, false);
        adapter = new KalendarAdapter(getActivity());
        DaggerApiComponent.builder().build().inject(this);
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
        getKalendarResponse(kalPref.clientToken());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SelectCalendarActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void getKalendarResponse(String clientToken) {
        backendApi.getCalendar(clientToken).enqueue(new Callback<GetKalendarListResponse>() {

            @Override
            public void onResponse(Call<GetKalendarListResponse> call, Response<GetKalendarListResponse> response) {
                adapter.addKalendarResponse(response.body().getKalendars());
            }

            @Override
            public void onFailure(Call<GetKalendarListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}