package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.services.GoogleApiService;

import static com.greenfox.kalendaryo.services.GoogleApiService.finish;

public class SettingsFragment extends Fragment {

    private KalPref kalPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        Button signOut = (Button) view.findViewById(R.id.bn_logout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        kalPref = new KalPref(getContext());
        return view;
    }

    public void signOut() {
        GoogleApiService.getInstance().getGoogleApiClient().connect();
        GoogleApiService.getInstance().getGoogleApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                if(GoogleApiService.getInstance().getGoogleApiClient().isConnected()) {
                    Auth.GoogleSignInApi.signOut(GoogleApiService.getInstance().getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.d("Log out", "User Logged out");
                                finish();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
            @Override
            public void onConnectionSuspended(int i) {
                Log.d("Connection suspended", "Google API Client Connection Suspended");
            }
        });
        kalPref.clearAccountsAndAll();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
