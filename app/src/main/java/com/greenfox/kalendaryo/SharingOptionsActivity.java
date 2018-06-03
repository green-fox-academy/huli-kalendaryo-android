package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.greenfox.kalendaryo.adapter.SharingOptionsAdapter;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.ArrayList;
import java.util.List;

public class SharingOptionsActivity extends AppCompatActivity {

    private SharingOptionsAdapter adapter;
    Button goToChooseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Kalendar kalMerged = (Kalendar) getIntent().getSerializableExtra("list");
        setContentView(R.layout.activity_sharing_options);

        adapter = new SharingOptionsAdapter(this, kalMerged);

        RecyclerView recKal = findViewById(R.id.sharing_options_list);
        recKal.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recKal.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recKal.getContext(),
                        recyclerLayoutManager.getOrientation());
        recKal.addItemDecoration(dividerItemDecoration);
        goToChooseAccount = findViewById(R.id.gotochooseaccount);
        goToChooseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SharingOptionsActivity.this, ChooseAccountActivity.class);
                i.putExtra("list", kalMerged);
                startActivity(i);
                adapter.removeInputCalendarIds();
            }
        });
    }
}
