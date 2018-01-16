package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.kalendaryo.models.Kalendar;

public class SelectCalendarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);

        ListView listPage = findViewById(R.id.listPage);
        Button merge = findViewById(R.id.mergeSelectedCalendars);
        merge.setOnClickListener(this);

        listPage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String s = listPage.getItemAtPosition(i).toString();

                // Toast.makeText(this, "Calendars selected", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mergeSelectedCalendars:
                Intent selectAccountActivity = new Intent(SelectCalendarActivity.this, ChooseAccountActivity.class);
                startActivity(selectAccountActivity);
                break;
        }
    }
}