package com.greenfox.kalendaryo.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.MainActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.KalPref;

import static com.greenfox.kalendaryo.services.GoogleService.finish;

public class LogoutService {

    KalPref kalPref;

    public void logOut(Context context) {

        kalPref = new KalPref(context);

        GoogleService.getInstance().getGoogleApiClient().connect();
        GoogleService.getInstance().getGoogleApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                if (GoogleService.getInstance().getGoogleApiClient().isConnected()) {
                    Auth.GoogleSignInApi.signOut(GoogleService.getInstance().getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.d("Log out", "User Logged out");
                                Toast.makeText(context, "You successfully logged out", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
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
    }
}
