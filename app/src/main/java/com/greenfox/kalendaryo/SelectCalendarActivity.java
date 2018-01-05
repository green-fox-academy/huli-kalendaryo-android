package com.greenfox.kalendaryo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class SelectCalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_calendar);

        ListView first = findViewById(R.id.firstCalList);
        ListView second = findViewById(R.id.secondCalList);
        TextView firstEmail = findViewById(R.id.firstEmail);
        TextView secondEmail = findViewById(R.id.secondEmail);
        
    }

}
