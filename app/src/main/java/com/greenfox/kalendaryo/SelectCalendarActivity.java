package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Parcelable;
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
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GoogleCalendarsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectCalendarActivity extends AppCompatActivity {

    private KalPref kalPref;
    private GoogleCalendarAdapter adapter;
    Button goToChooseAccount;
    Kalendar kalendar;
    RecyclerView recKal;
    List<WeekViewEvent> eventsFromGoogle = new ArrayList<>();
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    private ProgressBar progressBar;

    @Inject
    GoogleApi googleApi;

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
        recKal = findViewById(R.id.listView);
        recKal.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recKal.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recKal.getContext(),
                        recyclerLayoutManager.getOrientation());
        recKal.addItemDecoration(dividerItemDecoration);
        String customName = getIntent().getStringExtra(CustomNameActivity.CUSTOM_NAME);
        kalendar.setCustomName(customName);
        goToChooseAccount = findViewById(R.id.gotochooseaccount);
        goToChooseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kalendar.getInputGoogleCalendars().isEmpty()) {
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                    Intent i = new Intent(SelectCalendarActivity.this, ChooseAccountActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
                    i.putExtra("list", kalendar);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(SelectCalendarActivity.this, "Please select at least one calendar to merge",
                            Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }

    public void getCalendarList() {
        ArrayList<String> accounts = kalPref.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            GoogleAuth googleAuth = kalPref.getAuth(accounts.get(i));

            String accessToken = googleAuth.getAccessToken();
            String authorization = "Bearer " + accessToken;

            googleApi.getCalendarList(authorization).enqueue(new Callback<GoogleCalendarsResponse>() {
                @Override
                public void onResponse(Call<GoogleCalendarsResponse> call, Response<GoogleCalendarsResponse> response) {
                    adapter.addGoogleCalendars(response.body().getItems());
                    googleCalendars.addAll(response.body().getItems());
                }

                @Override
                public void onFailure(Call<GoogleCalendarsResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}
