package com.greenfox.kalendaryo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout profileSection;
    private Button signOut;
    private TextView loginName, loginEmail, token, myText;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileSection = findViewById(R.id.prof_section);
        signOut = findViewById(R.id.bn_logout);
        loginName = findViewById(R.id.name);
        loginEmail = findViewById(R.id.email);
        token = findViewById(R.id.tokenText);
        myText = findViewById(R.id.myText);
        signOut.setOnClickListener(this);
        profileSection.setVisibility(View.GONE);
        findViewById(R.id.button2).setOnClickListener(this);
        settingDisplayNameAndEamil(useremail,userName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bn_logout:
                signOut();
                break;
            case R.id.button2:
                displayData();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void signOut() {
        sharedPref.edit().clear().apply();
        updateUI(false);
    }

    public void updateUI(boolean isLogin) {
        if (isLogin) {
            profileSection.setVisibility(View.VISIBLE);
            signIn.setVisibility(View.GONE);
            myText.setVisibility(View.VISIBLE);
            token.setVisibility(View.VISIBLE);
        } else {
            profileSection.setVisibility(View.GONE);
            signIn.setVisibility(View.VISIBLE);
            myText.setVisibility(View.GONE);
            token.setVisibility(View.GONE);
        }
    }

    public void displayData() {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        String accessToken = sharedPref.getString("token", "");
        myText.setText(email);
        token.setText(accessToken);
    }

    private void settingDisplayNameAndEamil(String userName, String userEmail) {
        loginName.setText(userName);
        loginEmail.setText(userEmail);
    }
}
