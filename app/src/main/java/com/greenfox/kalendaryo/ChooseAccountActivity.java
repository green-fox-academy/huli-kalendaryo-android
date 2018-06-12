package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.BackendApi;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;
import com.greenfox.kalendaryo.services.AccountService;
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
            Intent i = new Intent(ChooseAccountActivity.this, StaticWeekViewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("googleCalendars", (ArrayList<? extends Parcelable>) googleCalendars);
            i.putExtra(SelectCalendarActivity.KALENDAR, kalendar);
            i.putExtras(bundle);
            startActivity(i);
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
