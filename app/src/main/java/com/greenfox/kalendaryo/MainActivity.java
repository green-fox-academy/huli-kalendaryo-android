package com.greenfox.kalendaryo;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.greenfox.kalendaryo.services.GoogleApiService;

import com.greenfox.kalendaryo.adapter.KalendarAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signOut, addAccount, showCalendars;
    private TextView loginName, loginEmail, token, myText;
    private RecyclerView listView;
    private KalendarAdapter adapter;
    private KalPref kalPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signOut = findViewById(R.id.bn_logout);
        addAccount = findViewById(R.id.choose_account);
        loginName = findViewById(R.id.name);
        loginEmail = findViewById(R.id.email);
        token = findViewById(R.id.tokenText);
        myText = findViewById(R.id.myText);
        adapter = new KalendarAdapter(this);
        showCalendars = findViewById(R.id.showCalendars);
        showCalendars.setOnClickListener(this);
        listView = findViewById(R.id.apilistcalendars);
        listView.setAdapter(adapter);
        signOut.setOnClickListener(this);
        addAccount.setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        kalPref = new KalPref(this.getApplicationContext());
        kalPref.clearAccountsAndAll();
        checkSharedPreferencesForUser();
        settingDisplayNameAndEmail(kalPref.getString("email"),kalPref.getString("username"));
        if(getIntent().getStringExtra("googleAccountName")!= null){
            TextView newaccountName = findViewById(R.id.new_accountname);
            newaccountName.setText(getIntent().getStringExtra("googleAccountName"));
        }
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
                Intent i = new Intent(this, LoginActivity.class);
                i.putExtra("ifNewAccChoosen", true);
                startActivity(i);
                break;
            case R.id.mergeCalsButton:
                createMergedCals();
        }
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
        kalPref.clearAccountsAndAll();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void checkSharedPreferencesForUser() {

        ArrayList<String> accounts = kalPref.getAccounts();
        if (accounts.size() == 0) {
            Toast.makeText(this, "You have to log in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void displayData() {
        ArrayList<String> accounts = kalPref.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            KalAuth kalAuth = kalPref.getAuth(accounts.get(i));
            myText.append(kalAuth.getEmail() + "\n");
            token.append(kalAuth.getAccessToken() + "\n");
        }
    }

    private void settingDisplayNameAndEmail(String userName, String userEmail) {
        loginName.setText(userName);
        loginEmail.setText(userEmail);

    }

    public void createMergedCals() {
        Intent intent = new Intent(this, SelectCalendarActivity.class);
        startActivity(intent);
    }
}
