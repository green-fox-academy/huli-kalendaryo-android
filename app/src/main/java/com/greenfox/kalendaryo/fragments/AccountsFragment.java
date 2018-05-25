package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.KalPref;

import java.util.List;

import javax.inject.Inject;


public class AccountsFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    KalPref kalPref;
    FloatingActionButton floatingActionButton;
    AccountAdapter accountAdapter;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listaccounts, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        accountAdapter = new AccountAdapter(getActivity(), false);

        recyclerViewSetup(view);

        kalPref = new KalPref(this.getContext());
        floatingActionButton = view.findViewById(R.id.addNewAccount);
        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            i.putExtra("ifNewAccChoosen", true);
            startActivity(i);
        });
        List<GoogleAuth> auths = kalPref.getGoogleAuths();
        accountAdapter.addAll(auths);
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void recyclerViewSetup(View view) {
        recyclerView = view.findViewById(R.id.accountsRecycleView);
        recyclerView.setAdapter(accountAdapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
