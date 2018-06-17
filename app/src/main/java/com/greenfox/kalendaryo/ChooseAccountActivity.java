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
import android.widget.Toast;

import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.event.PreviewEvent;
import com.greenfox.kalendaryo.services.AccountService;
import com.greenfox.kalendaryo.services.EventService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;


public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    KalPref kalpref;
    List<GoogleCalendar> googleCalendars = new ArrayList<>();
    Button buttonNext;
    Kalendar kalendar;
    private ProgressBar progressBar;
    private List<PreviewEvent> previewEvents = new ArrayList<>();

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
            Toast.makeText(this, "Please wait until preview gets displayed", Toast.LENGTH_LONG).show();

            launchEventService();

            previewEvents = getEventsfromEventService();

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
                }, 5000);
        });

        accountService.listAccountsFromBackend(setRecyclerView(), false, getIntent());
    }

    public RecyclerView setRecyclerView() {
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview = findViewById(R.id.view_accounts);
        recyclerview.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerview.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerview.addItemDecoration(dividerItemDecoration);
        return recyclerview;
    }

    public void launchEventService() {
        Intent intentToEventService = new Intent(ChooseAccountActivity.this, EventService.class);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
        intentToEventService.putExtras(bundle2);
        startService(intentToEventService);
    }

    public List<PreviewEvent> getEventsfromEventService() {
        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                previewEvents = (List<PreviewEvent>) intent.getSerializableExtra("weekViewEvents");
            }
        };
        LocalBroadcastManager.getInstance(ChooseAccountActivity.this).registerReceiver(mMessageReceiver, new IntentFilter("weekViewEvents"));
        return previewEvents;
    }

}
