package com.greenfox.kalendaryo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alamkanak.weekview.WeekViewEvent;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.event.EventResponse;
import com.greenfox.kalendaryo.models.event.PreviewEvent;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;
import com.greenfox.kalendaryo.services.AccountService;
import com.greenfox.kalendaryo.services.BackgroundService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    KalPref kalpref;
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    Button buttonNext;
    Kalendar kalendar;
    private ProgressBar progressBar;
    private GoogleApi googleApi;
    private List<PreviewEvent> eventsFromGoogle = new ArrayList<>();
    private List<PreviewEvent> weekViewEvents = new ArrayList<>();
    List<PreviewEvent> previewEvents = new ArrayList<>();

    @Inject
    BackendApi backendApi;

    @Inject
    AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        kalpref = new KalPref(this.getApplicationContext());
        buttonNext = findViewById(R.id.button_next);


        DaggerApiComponent.builder().build().inject(this);
        kalendar = (Kalendar) getIntent().getSerializableExtra(SelectCalendarActivity.KALENDAR);
        googleCalendars = kalendar.getInputGoogleCalendars();
        String clientToken = kalpref.clientToken();

        GoogleCalendar[] array = new GoogleCalendar[kalendar.getInputGoogleCalendars().size()];
        for (int j = 0; j < kalendar.getInputGoogleCalendars().size(); j++) {
            array[j] = kalendar.getInputGoogleCalendars().get(j);
        }
        kalendar.setInputGoogleCalendars(Arrays.asList(array));
        buttonNext.setOnClickListener(v -> {
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

                Intent intentToService = new Intent(ChooseAccountActivity.this, BackgroundService.class);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
                intentToService.putExtras(bundle2);
                startService(intentToService);

            BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    System.out.println("I RECEIVED IT!!!!");
                    previewEvents = (List<PreviewEvent>) intent.getSerializableExtra("weekViewEvents");
                }
            };
            LocalBroadcastManager.getInstance(ChooseAccountActivity.this).registerReceiver(mMessageReceiver, new IntentFilter("weekViewEvents"));


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ChooseAccountActivity.this, WeekViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
                    i.putExtra("list", kalendar);
                    i.putExtras(bundle);
                    i.putExtra("weekViewEvents", (Serializable) previewEvents);
                    startActivity(i);
                }
            }, 10000);

                finish();
            });

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview = findViewById(R.id.view_accounts);
        recyclerview.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerview.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerview.addItemDecoration(dividerItemDecoration);

        accountService.listAccountsFromBackend(recyclerview, false, getIntent());
    }

}
