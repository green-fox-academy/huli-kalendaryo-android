package com.greenfox.kalendaryo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
