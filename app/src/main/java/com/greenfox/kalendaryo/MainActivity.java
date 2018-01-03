package com.greenfox.kalendaryo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.services.calendar.model.Calendar;
import com.greenfox.kalendaryo.adapter.CalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;

import java.util.List;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signOut, showCalendars;
    private TextView loginName, loginEmail, token, myText;
    private SharedPreferences sharedPref;
    private ListView listView;
    private CalendarAdapter adapter;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout profileSection = findViewById(R.id.prof_section);
        signOut = findViewById(R.id.bn_logout);
        loginName = findViewById(R.id.name);
        loginEmail = findViewById(R.id.email);
        token = findViewById(R.id.tokenText);
        myText = findViewById(R.id.myText);
        showCalendars = findViewById(R.id.showCalendars);
        listView = findViewById(R.id.apilistcalendars);
        showCalendars.setOnClickListener(this);
        signOut.setOnClickListener(this);
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
            case R.id.showCalendars:
                getCalendarList();
                break;
        }
    }

    public void signOut() {
        sharedPref.edit().clear().apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void checkSharedPreferencesForUser() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("usename", "");
        if (sharedPref.getString("username", "").equals("")) {
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

    public void getCalendarList() {
    adapter = new CalendarAdapter(this);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("10.27.6.188")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    service = retrofit.create(ApiService.class);

        service.getCalendarList(sharedPref.getString("token", "")).enqueue(new Callback<List<Calendar>>() {
            @Override
            public void onResponse(Call<List<Calendar>> call, Response<List<Calendar>> response) {
                
            }

            @Override
            public void onFailure(Call<List<Calendar>> call, Throwable t) {

            }
        });

        listView.setAdapter(adapter);

        adapter.clear();

        adapter.addAll();
    }
}
