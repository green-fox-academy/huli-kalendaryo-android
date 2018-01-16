package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.TabViewActivity;
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.adapter.AccountsList;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.services.GoogleApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountsFragment extends Fragment {

    ListView accountNamesView;
    AccountsList adapter;
    KalPref kalpref;
    FloatingActionButton floatingActionButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listaccounts, container, false);
        kalpref = new KalPref(getActivity());
        adapter = new AccountsList(view.getContext());
        accountNamesView = view.findViewById(R.id.allaccounts);
        accountNamesView.setAdapter(adapter);
        floatingActionButton = view.findViewById(R.id.addNewAccount);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        List<KalAuth> auths = kalpref.getKalAuths();

        adapter.addAll(auths);


        return view;
    }
}
