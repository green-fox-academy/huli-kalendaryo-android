package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.http.RetrofitClient;
import com.greenfox.kalendaryo.http.google.GoogleApi;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.event.EventResponse;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView accountNamesView;
    KalPref kalpref;
    Button next;
    GoogleApi googleApi;
    KalMerged kalMerged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kalMerged = (KalMerged) getIntent().getSerializableExtra("list");

        setContentView(R.layout.activity_choose_account);
        kalpref = new KalPref(this.getApplicationContext());
        next = findViewById(R.id.gottoweekview);

        String clientToken = kalpref.clientToken();

        String[] array = new String[kalMerged.getInputCalendarIds().size()];
        for (int j = 0; j < kalMerged.getInputCalendarIds().size(); j++) {
            array[j] = kalMerged.getInputCalendarIds().get(j);
        }

        kalMerged.setInputCalendarIds(Arrays.asList(array));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String calendarId : kalMerged.getInputCalendarIds()) {
                    getEventResponse(clientToken, calendarId);
                }
                Intent i = new Intent(ChooseAccountActivity.this, AsynchActivity.class);
                i.putExtra("list", kalMerged);
                startActivity(i);
            }
        });

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);

        accountNamesView = findViewById(R.id.accountNames);
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);

        AccountAdapter accountAdapter = new
                AccountAdapter(kalpref.getKalAuths(), this);

        accountAdapter.setEmailChange(new AccountAdapter.EmailChange() {
            @Override
            public void emailChanged(String email) {
                kalMerged.setOutputCalendarId(email);
            }
        });
        accountNamesView.setAdapter(accountAdapter);
    }

    private void getEventResponse(String clientToken, String calendarId) {
        googleApi = RetrofitClient.getGoogleEvents();
        googleApi.getEventList(clientToken, calendarId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
