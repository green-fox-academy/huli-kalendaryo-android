package com.greenfox.kalendaryo;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.api.services.calendar.model.Calendar;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCalendars extends AppCompatActivity {

    ListView viewListOfCalendars = (ListView) findViewById(R.id.apilistcalendars);

    public void getCalendars(View view) throws IOException {

        ListCalendarsInterface listCalendarsInterface = ListCalendarsInterface.retrofit.create(ListCalendarsInterface.class);
        Call<List<com.google.api.services.calendar.model.Calendar>> call = listCalendarsInterface
                .calendars("141350348735-cibla76rafmvq6c6enon40kc6eg3r9su.apps.googleusercontent.com");

        call.enqueue(new Callback<List<Calendar>>() {
            @Override
            public void onResponse(Call<List<Calendar>> call, Response<List<Calendar>> response) {

                List<Calendar> listOfCalendars = response.body();

                if (listOfCalendars == null) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                } else {
                    String[] myList = new String[listOfCalendars.size()];
                    for (int i = 0; i < listOfCalendars.size(); i++) {
                        myList[i] = listOfCalendars.get(i).getId();
                        viewListOfCalendars.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, myList));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Calendar>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

//        Call<com.google.api.services.calendar.model.Calendar> call = listCalendarsInterface.calendars("141350348735-cibla76rafmvq6c6enon40kc6eg3r9su.apps.googleusercontent.com");
//        Calendar content = call.execute().body();
//        return (content.toString());
    }

}






