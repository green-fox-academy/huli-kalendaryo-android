package com.greenfox.kalendaryo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.kalendaryo.models.KalPref;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signOut;
    private TextView loginName, loginEmail, token, myText;
 //   private SharedPreferences sharedPref;
    private Button mergeCalsButton;
    private KalPref kalPref;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout profileSection = findViewById(R.id.prof_section);
        signOut = findViewById(R.id.bn_logout);
        loginName = findViewById(R.id.name);
        loginEmail = findViewById(R.id.email);
        token = findViewById(R.id.tokenText);
        myText = findViewById(R.id.myText);
        signOut.setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        checkSharedPreferencesForUser();
        settingDisplayNameAndEamil(kalPref.getString("email"),kalPref.getString("username"));
        mergeCalsButton = findViewById(R.id.mergeCalsButton);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bn_logout:
                signOut();
                break;
            case R.id.button2:
                displayData();
                break;
            case R.id.mergeCalsButton:
                createMergedCals();
        }
    }

    public void signOut() {
        kalPref.removeAccount("");
 //       sharedPref.edit().clear().apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void checkSharedPreferencesForUser() {

        String username = kalPref.getString("usename");
        if (username.equals("")) {
            Toast.makeText(this, "You have to log in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void displayData() {
        String email = kalPref.getString("email");
        String accessToken = kalPref.getString("accesstoken");
        myText.setText(email);
        token.setText(accessToken);
    }

    private void settingDisplayNameAndEamil(String userName, String userEmail) {
        loginName.setText(userName);
        loginEmail.setText(userEmail);
    }

    public void createMergedCals() {
        Intent intent = new Intent(this, SelectCalendarActivity.class);
        startActivity(intent);
    }
}
