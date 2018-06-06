package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.greenfox.kalendaryo.adapter.GoogleCalendarAdapter;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GoogleCalendarsResponse;
import com.greenfox.kalendaryo.services.AccountService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectCalendarActivity extends AppCompatActivity {

    private static int FIRST_ATTEMPT = 1;
    private static int FINAL_ATTEMPT = 2;
    private static int ATTEMPT_STEP = 1;
    private KalPref kalPref;
    private GoogleCalendarAdapter adapter;
    Button buttonNext;
    Kalendar kalendar;
    RecyclerView recyclerView;
    List<WeekViewEvent> eventsFromGoogle = new ArrayList<>();
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    private ProgressBar progressBar;
    public static final String KALENDAR = "com.greenfox.kalendaryo.KALENDAR";

    @Inject
    GoogleApi googleApi;

    @Inject
    BackendApi backendApi;

    @Inject
    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);
        DaggerApiComponent.builder().build().inject(this);
        adapter = new GoogleCalendarAdapter(this);
        kalPref = new KalPref(this.getApplicationContext());
        kalendar = new Kalendar();
        getCalendarList();
        adapter.setListChange(kalendar);
        recyclerView = findViewById(R.id.view_calendars);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        String customName = getIntent().getStringExtra(CustomNameActivity.CUSTOM_NAME);
        kalendar.setCustomName(customName);
        buttonNext = findViewById(R.id.button_next);
        buttonNext.setOnClickListener(v -> {
            if (!kalendar.getInputGoogleCalendars().isEmpty()) {
                progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(SelectCalendarActivity.this, SharingOptionsActivity.class);
                i.putExtra(KALENDAR, kalendar);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(SelectCalendarActivity.this, "Please select at least one calendar to merge",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCalendarList() {
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            String account = accounts.get(i);
            requestCalendars(account, FIRST_ATTEMPT);
        }
    }
    public void requestCalendars (String account, Integer attempt) {
        GoogleAuth googleAuth = kalPref.getAuth(account);
        String authorization = "Bearer " + googleAuth.getAccessToken();
        googleApi.getCalendarList(authorization).enqueue(new Callback<GoogleCalendarsResponse>() {
            @Override
            public void onResponse(Call<GoogleCalendarsResponse> call, Response<GoogleCalendarsResponse> response) {
                if (response.errorBody() == null) {
                    adapter.addGoogleCalendars(response.body().getItems());
                    googleCalendars.addAll(response.body().getItems());
                } else if (attempt != FINAL_ATTEMPT){
                    requestAccessTokenRefresh(googleAuth, kalPref.clientToken());
                    requestCalendars(account, attempt + ATTEMPT_STEP);
                }
            }

            @Override
            public void onFailure(Call<GoogleCalendarsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void requestAccessTokenRefresh (GoogleAuth googleAuth, String clientToken) {
        backendApi.refreshAccessToken(clientToken, googleAuth.getEmail()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    googleAuth.setAccessToken(response.body().string());
                    kalPref.putAuth(googleAuth);
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}