package com.greenfox.kalendaryo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.CalendarList;

import com.greenfox.kalendaryo.adapter.CalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalModel;
import com.greenfox.kalendaryo.models.Kalendar;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signOut, showCalendars;
    private TextView loginName, loginEmail, token, myText;
    private SharedPreferences sharedPref;
    private ListView listView;
    private CalendarAdapter adapter;
    private ApiService apiService;
    HttpTransport httpTransport;
    JsonFactory jsonFactory;
    Credential credentials;

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

        apiService = RetrofitClient.getApi("https://www.googleapis.com/calendar/v3/users/me/");

        String accessToken = sharedPref.getString("token", "");

        apiService.getCalendarList(accessToken).enqueue(new Callback<KalModel>() {
            @Override
            public void onResponse(Call<KalModel> call, Response<KalModel> response) {

                KalModel kalModel = response.body();

                List<Kalendar> kalendars = kalModel.getItems();

                for (Kalendar kalendar: kalendars) {
                    adapter.add(kalendar);
                }

                listView.setAdapter(adapter);
                adapter.clear();

            }

            @Override
            public void onFailure(Call<KalModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
