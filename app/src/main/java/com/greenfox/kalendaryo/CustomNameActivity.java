package com.greenfox.kalendaryo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class CustomNameActivity extends AppCompatActivity {

    Button buttonNext;
    public static final String CUSTOM_NAME = "com.greenfox.kalendaryo.CUSTOM_NAME";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_name);
        buttonNext = findViewById(R.id.button_next);
        buttonNext.setOnClickListener(view -> {
            progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            Intent i = new Intent(CustomNameActivity.this, SelectCalendarActivity.class);
            EditText enteredCustomName = findViewById(R.id.edit_kalendar_name);
            String customName = enteredCustomName.getText().toString();
            i.putExtra(CUSTOM_NAME, customName);
            startActivity(i);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onStop() {
        super.onStop();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }
}
