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
import android.widget.ProgressBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.services.AccountService;
import javax.inject.Inject;


public class AccountsFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    KalPref kalpref;
    FloatingActionButton buttonNext;
    RecyclerView recyclerView;

    @Inject
    AccountService accountService;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        kalpref = new KalPref(getActivity());
        DaggerApiComponent.builder().build().inject(this);
        recyclerView = view.findViewById(R.id.view_accounts);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        buttonNext = view.findViewById(R.id.button_next);
        accountService.listAccountsFromBackend(recyclerView, true, null);
        buttonNext.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            i.putExtra("ifNewAccChoosen", true);
            startActivity(i);
        });
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
