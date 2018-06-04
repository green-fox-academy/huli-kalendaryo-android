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
import com.greenfox.kalendaryo.models.Kalendar;

public class SharingOptionsActivity extends AppCompatActivity {

    private SharingOptionsAdapter adapter;
    Button goToChooseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Kalendar kalendar = (Kalendar) getIntent().getSerializableExtra(SelectCalendarActivity.KALENDAR);
        setContentView(R.layout.activity_sharing_options);

        adapter = new SharingOptionsAdapter(this, kalendar);

        RecyclerView recyclerView = findViewById(R.id.sharing_options_list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        goToChooseAccount = findViewById(R.id.button_gotochooseaccount);
        goToChooseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SharingOptionsActivity.this, ChooseAccountActivity.class);
                i.putExtra(SelectCalendarActivity.KALENDAR, kalendar);
                startActivity(i);
            }
        });
    }
}
