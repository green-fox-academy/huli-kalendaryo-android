package com.greenfox.kalendaryo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signIn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 900;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signIn = findViewById(R.id.bn_login);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfLoggedInAndSignIn();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void checkIfLoggedInAndSignIn() {
        if(googleApiClient == null) {
            GoogleSignInOptions signInOptions = new GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken("141350348735-p37itsqvg8599ebc3j9cr1eur0n0d1iv.apps.googleusercontent.com")
                    .requestServerAuthCode("141350348735-p37itsqvg8599ebc3j9cr1eur0n0d1iv.apps.googleusercontent.com")
                    .build();
            googleApiClient = new GoogleApiClient
                    .Builder(this)
                    .enableAutoManage(this,this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                    .build();
        }
        signIn();
    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    public void handleResult(GoogleSignInResult result) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String clientToken = sharedPref.getString("clienttoken", "");
        if (result.isSuccess()) {
            account = result.getSignInAccount();
            final String userName = account.getDisplayName();
            final String userEmail = account.getEmail();
            ApiService apiService = RetrofitClient.getApi();
            apiService.postAuth(clientToken, new KalAuth(account.getServerAuthCode(), userEmail, userName)).enqueue(new Callback<KalUser>() {
                @Override
                public void onResponse(Call<KalUser> call, Response<KalUser> response) {
                    String accessToken = response.body().getAccessToken();
                    String clientToken = response.body().getClientToken();
                    editSharedPref(userEmail, userName, accessToken, clientToken);
                    Log.d("shared", sharedPref.getString("email", ""));
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                @Override
                public void onFailure(Call<KalUser> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
        }
    }

    private void editSharedPref(String email, String userName, String accessToken, String clientToken) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPref.edit();
        editor.putString("email", email);
        editor.putString(email + "username", userName);
        editor.putString(email + "accesstoken", accessToken);
        editor.putString("clienttoken", clientToken);
        editor.apply();
    }
}
