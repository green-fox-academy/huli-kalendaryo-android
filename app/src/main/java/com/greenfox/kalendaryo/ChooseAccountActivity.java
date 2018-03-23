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
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;

import java.util.Arrays;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChooseAccountActivity extends AppCompatActivity {

    RecyclerView accountNamesView;
    KalPref kalpref;
    Button sendToBackend;
    Kalendar kalendar;

    @Inject
    BackendApi backendApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
        DaggerApiComponent.builder().build().inject(this);
        kalendar = (Kalendar) getIntent().getSerializableExtra("list");
        kalpref = new KalPref(this.getApplicationContext());
        sendToBackend = findViewById(R.id.sendtobackend);
        String clientToken = kalpref.clientToken();

        String[] array = new String[kalendar.getInputGoogleCalendars().size()];
        for (int j = 0; j < kalendar.getInputGoogleCalendars().size(); j++) {
            array[j] = kalendar.getInputGoogleCalendars().get(j);
        }

        kalendar.setInputGoogleCalendars(Arrays.asList(array));
        sendToBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendApi.postCalendar(clientToken, kalendar).enqueue(new Callback<PostKalendarResponse>() {
                    @Override
                    public void onResponse(Call<PostKalendarResponse> call, Response<PostKalendarResponse> response) {
                        PostKalendarResponse postKalendarResponse = response.body();

                    }

                    @Override
                    public void onFailure(Call<PostKalendarResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Intent i = new Intent(ChooseAccountActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        accountNamesView = findViewById(R.id.accountNames);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);
        AccountAdapter accountAdapter = new
                AccountAdapter(kalpref.getGoogleAuths(),this);

        accountAdapter.setEmailChange(new AccountAdapter.EmailChange() {
            @Override
            public void emailChanged(String email) {
                kalendar.setOutputGoogleAuthId(email);
            }
        });
        accountNamesView.setAdapter(accountAdapter);
    }
}
