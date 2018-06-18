package com.greenfox.kalendaryo.services;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;

public class GoogleApiService {

  private static final String CLIENT_ID = "141350348735-p37itsqvg8599ebc3j9cr1eur0n0d1iv.apps.googleusercontent.com";

  public void initializeGoogleService(Activity activity) {
    GoogleSignInOptions googleSignInOptions = buildSignInOptions();
    Context context = activity.getApplicationContext();
    FragmentActivity fragmentActivity = (FragmentActivity) activity;
    GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) activity;

    GoogleService.init(new GoogleApiClient
        .Builder(context)
        .enableAutoManage(fragmentActivity, onConnectionFailedListener)
        .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
        .build());
  }

  public GoogleSignInOptions buildSignInOptions() {
    return new GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestScopes(new Scope("https://www.googleapis.com/auth/calendar"))
        .requestEmail()
        .requestIdToken(CLIENT_ID)
        .requestServerAuthCode(CLIENT_ID)
        .build();
  }
}
