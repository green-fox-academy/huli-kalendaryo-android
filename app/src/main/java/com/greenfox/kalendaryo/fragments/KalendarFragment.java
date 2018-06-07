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
import android.widget.ProgressBar;

import com.greenfox.kalendaryo.CustomNameActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.backend.MockBackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetKalendarListResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    KalPref kalPref;
    FloatingActionButton buttonNext;
    private KalendarAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Inject
    BackendApi backendApi;

    @Inject
    MockBackendApi mockBackendApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kalendar, container, false);
        adapter = new KalendarAdapter(getActivity());
        DaggerApiComponent.builder().build().inject(this);
        buttonNext = view.findViewById(R.id.button_next);

        recyclerViewSetup(view);

        kalPref = new KalPref(this.getContext());
        getKalendarResponse(kalPref.clientToken());

        buttonNext.setOnClickListener(v -> {

            progressBar = view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            Intent i = new Intent(getActivity(), CustomNameActivity.class);
            startActivity(i);
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getKalendarResponse(kalPref.clientToken());
    }

    private void getKalendarResponse(String clientToken) {
        backendApi.getCalendar(clientToken).enqueue(new Callback<GetKalendarListResponse>() {

            @Override
            public void onResponse(Call<GetKalendarListResponse> call, Response<GetKalendarListResponse> response) {
                adapter.setKalendarResponses(response.body().getKalendars());
            }

            @Override
            public void onFailure(Call<GetKalendarListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void recyclerViewSetup(View view){
        recyclerView = view.findViewById(R.id.view_kalendars);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
