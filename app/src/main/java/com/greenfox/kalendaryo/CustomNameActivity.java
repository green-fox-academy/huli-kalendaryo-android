package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class CustomNameActivity extends AppCompatActivity {

    Button submitCustomName;
    public static final String CUSTOM_NAME = "com.greenfox.kalendaryo.CUSTOM_NAME";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_name);
        submitCustomName = findViewById(R.id.submitCustomName);
        submitCustomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = (ProgressBar)findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(CustomNameActivity.this, SelectCalendarActivity.class);
                EditText enteredCustomName = (EditText) findViewById(R.id.customName);
                String customName = enteredCustomName.getText().toString();
                i.putExtra(CUSTOM_NAME, customName);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onStop() {
        super.onStop();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }
}
