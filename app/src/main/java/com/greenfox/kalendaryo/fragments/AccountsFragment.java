package com.greenfox.kalendaryo.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.adapter.AccountsList;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.AuthResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountsFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    AccountsList adapter;
    AccountAdapter accountAdapter;
    KalPref kalpref;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    @Inject
    BackendApi backendApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listaccounts_recycler, container, false);
        kalpref = new KalPref(getActivity());
        List<GoogleAuth> auths = kalpref.getGoogleAuths();
        adapter = new AccountsList(view.getContext());
        DaggerApiComponent.builder().build().inject(this);
        accountAdapter = new AccountAdapter(auths, getActivity());
        recyclerView = view.findViewById(R.id.accountRecyclerView);
        recyclerView.setAdapter(accountAdapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        floatingActionButton = view.findViewById(R.id.addNewAccount);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.putExtra("ifNewAccChoosen", true);
                startActivity(i);
            }
        });

        adapter.addAll(auths);
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
