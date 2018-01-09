package com.greenfox.kalendaryo;

import android.accounts.AccountManager;
import android.app.Activity;
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
import com.google.android.gms.common.api.Scope;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.services.GoogleApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.accounts.AccountManager.newChooseAccountIntent;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signIn;
    private static final int REQ_CODE = 900;
    private static final int REQUEST_ACCOUNT_PICKER = 500;
    private static final String CLIENT_ID = "141350348735-p37itsqvg8599ebc3j9cr1eur0n0d1iv.apps.googleusercontent.com";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private GoogleSignInAccount account;
    private String googleAccountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signIn = findViewById(R.id.bn_login);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAccount();
            }
        });
        // By default it is false, because this is way
        if(getIntent().getBooleanExtra("ifNewAccChoosen", false)) {
            chooseAccount();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void buildGoogleApiClient() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .setAccountName(googleAccountName)
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar"))
                .requestEmail()
                .requestIdToken(CLIENT_ID)
                .requestServerAuthCode(CLIENT_ID)
                .build();
        if(GoogleApiService.getGoogleApiClient() == null){
            GoogleApiService.init(new GoogleApiClient
                    .Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                    .build());
        }
        signIn();
    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(GoogleApiService.getInstance().getGoogleApiClient());
        startActivityForResult(intent, REQ_CODE);
    }

    private void chooseAccount() {
        startActivityForResult(newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null), REQUEST_ACCOUNT_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleResult(result);
                break;

            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == Activity.RESULT_OK && data != null && data.getExtras() != null) {
                    googleAccountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                    if (googleAccountName != null) {
                        buildGoogleApiClient();
                    }
                }
                break;
        }
    }

    public void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            account = result.getSignInAccount();
            final String userName = account.getDisplayName();
            final String userEmail = account.getEmail();
            ApiService apiService = RetrofitClient.getApi("backend");
            sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String clientToken = sharedPref.getString("clienttoken", "");
            apiService.postAuth(clientToken, new KalAuth(account.getServerAuthCode(), userEmail, userName)).enqueue(new Callback<KalUser>() {
              
                @Override
                public void onResponse(Call<KalUser> call, Response<KalUser> response) {
                    String accessToken = response.body().getAccessToken();
                    String clientToken = response.body().getClientToken();
                    editSharedPref(userEmail, userName, accessToken, clientToken);
                    Log.d("shared", sharedPref.getString("email", ""));
                    Intent signIn = new Intent(LoginActivity.this, MainActivity.class);
                    signIn.putExtra("googleAccountName", googleAccountName);
                    startActivity(signIn);
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
        sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("email", email);
        editor.putString("username", userName);
        editor.putString("accesstoken", accessToken);
        editor.putString("clienttoken", clientToken);
        editor.apply();
    }
}
