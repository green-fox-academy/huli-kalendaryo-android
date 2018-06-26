package com.greenfox.kalendaryo;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.adapter.KalendarSettingsAdapter;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetKalendarResponse;
import com.greenfox.kalendaryo.services.LogoutService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationAndDeleteActivity extends AppCompatActivity {

    private GetKalendarResponse getKalendarResponse;
    private KalPref kalPref;

    @Inject
    BackendApi backendApi;

    @Inject
    LogoutService logoutService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_and_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        getKalendarResponse = (GetKalendarResponse) i.getSerializableExtra(KalendarAdapter.GET_KALENDAR_RESPONSE);

        DaggerApiComponent.builder().build().inject(this);

        TextView kalendarName = findViewById(R.id.text_kalendar_name);
        kalendarName.setText(getKalendarResponse.getOutputCalendarId());
        TextView kalendarUser = findViewById(R.id.text_kalendar_account);
        kalendarUser.setText(getKalendarResponse.getOutputGoogleAuthId());

        recyclerViewSetup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.log_out) {
            logoutService.logOut(this);
        }
        return true;
    }

    public void deleteKalendarPopUp(View view) {
        long idToDelete = getKalendarResponse.getId();
        deleteKalendarPopUp(view, idToDelete);
    }

    public void deleteKalendarPopUp(View view, long idToDelete) {
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle(R.string.warning);
        alert.setMessage(R.string.surely_delete_kalendar);
        alert.setPositiveButton(R.string.yes, (dialogInterface, i) -> {
            kalPref = new KalPref(view.getContext());
            String clientToken = kalPref.clientToken();

            backendApi.deleteKalendar(clientToken, idToDelete).enqueue(new Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Intent i = new Intent(InformationAndDeleteActivity.this, MainActivity.class);
                    startActivity(i);
                    toastMessage(view, getString(R.string.successful_kalendar_deletion));
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                    toastMessage(view, getString(R.string.unsuccessful_kalendar_deletion));
                }
            });
            dialogInterface.dismiss();
        });

        alert.setNegativeButton(R.string.no, (dialogInterface, i) -> dialogInterface.dismiss());

        alert.show();
    }

    public void toastMessage(View view, String input) {
        Toast.makeText(view.getContext(), input,
                Toast.LENGTH_LONG).show();
    }

    public void recyclerViewSetup() {
        RecyclerView recyclerView = findViewById(R.id.view_source_calendars);
        List<String> inputGoogleCalendars = getKalendarResponse.getInputGoogleCalendars();
        KalendarSettingsAdapter kalendarSettingsAdapter = new KalendarSettingsAdapter(this);
        kalendarSettingsAdapter.setCalendarNames(inputGoogleCalendars);
        recyclerView.setAdapter(kalendarSettingsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
