package com.greenfox.kalendaryo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.greenfox.kalendaryo.services.GoogleApiService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signOut, addAccount;
    private TextView loginName, loginEmail, token, myText;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout profileSection = findViewById(R.id.prof_section);
        signOut = findViewById(R.id.bn_logout);
        addAccount = findViewById(R.id.choose_account);
        loginName = findViewById(R.id.name);
        loginEmail = findViewById(R.id.email);
        token = findViewById(R.id.tokenText);
        myText = findViewById(R.id.myText);
        signOut.setOnClickListener(this);
        addAccount.setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        checkSharedPreferencesForUser();
        settingDisplayNameAndEamil(sharedPref.getString("email", ""),sharedPref.getString("username", ""));
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
            case R.id.choose_account:

                break;
        }
    }

    public void signOut() {
        sharedPref.edit().clear().apply();
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
                                GoogleApiService.finish();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
    }

    private void checkSharedPreferencesForUser() {
        sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
        if (sharedPref.getString("email", "").equals("")) {
            Toast.makeText(this, "You have to log in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void displayData() {
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
