package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Ezzo on 2017. 12. 12..
 */

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout Prof_Section;
    private Button SignOut;
    private SignInButton SignIn;
    private TextView Name,Email;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Prof_Section = findViewById(R.id.prof_section);
        SignOut = findViewById(R.id.bn_logout);
        SignIn = (SignInButton) findViewById(R.id.bn_login);
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient
                .Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bn_login:
                signIn();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent()

    }

    public void signOut() {

    }

    public void handleResult(GoogleSignInResult result) {

    }

    public void updateUI(boolean isLogin) {

    }
}
