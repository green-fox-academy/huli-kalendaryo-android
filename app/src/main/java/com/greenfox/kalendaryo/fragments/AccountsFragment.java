package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.adapter.AccountsList;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetAccountResponse;
import com.greenfox.kalendaryo.services.AccountService;

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
    AccountService accountService;

    @Inject
    BackendApi backendApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listaccounts_recycler, container, false);
        kalpref = new KalPref(getActivity());
        DaggerApiComponent.builder().build().inject(this);
        recyclerView = view.findViewById(R.id.accountRecyclerView);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        floatingActionButton = view.findViewById(R.id.addNewAccount);
        getAccountResponse(kalpref.clientToken());
        //accountService.listAccountsFromBackend(kalpref.clientToken(), getActivity(), recyclerView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.putExtra("ifNewAccChoosen", true);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }


    public void getAccountResponse(String clientToken) {
        backendApi.getAccount(clientToken).enqueue(new Callback<GetAccountResponse>() {
            @Override
            public void onResponse(Call<GetAccountResponse> call, Response<GetAccountResponse> response) {
                GetAccountResponse getAccountResponse = response.body();
                accountAdapter = new AccountAdapter(getAccountResponse.getGoogleAuths(), getActivity());
                recyclerView.setAdapter(accountAdapter);
            }

            @Override
            public void onFailure(Call<GetAccountResponse> call, Throwable t) {

            }
        });
    }
}
