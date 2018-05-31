package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeScreenActivity extends AppCompatActivity {

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        String userGivenName = getIntent().getStringExtra("userGivenName");
        user = findViewById(R.id.user);
        user.setText(userGivenName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startActivity = new Intent(WelcomeScreenActivity.this, MainActivity.class);
                startActivity(startActivity);
                finish();
            }
        }, 2000);
    }
}
