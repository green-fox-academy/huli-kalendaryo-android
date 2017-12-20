package com.greenfox.kalendaryo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.api.services.calendar.model.Calendar;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";
    private LinearLayout profileSection;
    private Button signOut;
    private SignInButton signIn;
    private TextView loginName, loginEmail;
    private GoogleApiClient googleApiClient;
    private TextView myText;
    private static final int REQ_CODE = 900;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private TextView token;
    private ListView viewListOfCalendars;
    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileSection = findViewById(R.id.prof_section);
        signOut = findViewById(R.id.bn_logout);
        signIn = findViewById(R.id.bn_login);
        loginName = findViewById(R.id.name);
        loginEmail = findViewById(R.id.email);
        signIn.setOnClickListener(this);
        signOut.setOnClickListener(this);
        profileSection.setVisibility(View.GONE);
        myText = findViewById(R.id.myText);
        token = findViewById(R.id.tokenText);
        findViewById(R.id.button2).setOnClickListener(this);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("141350348735-cibla76rafmvq6c6enon40kc6eg3r9su.apps.googleusercontent.com")
                .requestServerAuthCode("141350348735-cibla76rafmvq6c6enon40kc6eg3r9su.apps.googleusercontent.com")
                .build();

        googleApiClient = new GoogleApiClient
                .Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();

        mApiInterface = ApiUtils.getApiInterface();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bn_login:
                signIn();
                break;
            case R.id.bn_logout:
                signOut();
                break;
            case R.id.button2:
                displayData();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    public void handleResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String userName = account.getDisplayName();
            String userEmail = account.getEmail();
            mApiInterface.sendAuthCode(new KalAuth(account.getServerAuthCode(), userEmail, userName)).enqueue(new Callback<KalUser>() {
                @Override
                public void onResponse(Call<KalUser> call, Response<KalUser> response) {
                    Log.d("give me token", "accessToken: " + response.body().accessToken);
                }
                @Override
                public void onFailure(Call<KalUser> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            loginName.setText(userName);
            loginEmail.setText(userEmail);
            sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            editor = sharedPref.edit();
            editor.putString("username", userEmail);
            editor.apply();
            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    public void updateUI(boolean isLogin) {
        if (isLogin) {
            profileSection.setVisibility(View.VISIBLE);
            signIn.setVisibility(View.GONE);
            myText.setVisibility(View.VISIBLE);
        } else {
            profileSection.setVisibility(View.GONE);
            signIn.setVisibility(View.VISIBLE);
            myText.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            //backend has to create endpoint to receive data
            //authcode comes here
            //send it to backend to get back TOKEN
            //once TOKEN is back send user data (email,name, bla bla bla)
            // ++ store TOKEN in sharedpre
            handleResult(result);
        }
    }

    public void displayData() {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedPref.getString("username", "");
        myText.setText(name + " ");
    }
}
