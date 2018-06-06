package com.greenfox.kalendaryo;

import android.accounts.AccountManager;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.GoogleAuth;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.KalUser;
import com.greenfox.kalendaryo.services.GoogleService;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.accounts.AccountManager.newChooseAccountIntent;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signIn;
    private ImageView logo;
    private static final int REQ_CODE = 900;
    private static final int REQUEST_ACCOUNT_PICKER = 500;
    private static final String CLIENT_ID = "141350348735-p37itsqvg8599ebc3j9cr1eur0n0d1iv.apps.googleusercontent.com";
    private KalPref kalPref;
    private GoogleAuth googleAuth;
    private ProgressBar progressBar;
    private Animation fromLeft;
    private Animation fromRight;


    @Inject
    BackendApi backendApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerApiComponent.builder().build().inject(this);
        kalPref = new KalPref(this.getApplicationContext());
        signIn = findViewById(R.id.button_login);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
                buildGoogleApiClient(false);
            }
        });

        // By default it is false, because this is way
        if (getIntent().getBooleanExtra("ifNewAccChoosen", false)) {
            buildGoogleApiClient(true);
            signIn.setVisibility(View.GONE);
        } else if (getIntent().getBooleanExtra("isLoggedOut", false) == false) {
            logo = findViewById(R.id.icon_logo);
            Animation fromLeft = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.from_left);
            logo.setAnimation(fromLeft);
            fromRight = AnimationUtils.loadAnimation(this, R.anim.from_right);
            signIn.setAnimation(fromRight);
            setBrandNameAnimation();
        }
    }

    private void setBrandNameAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.text_brand), "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1200);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.text_brand), "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1200);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.text_brand), "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(1200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(500);
        animatorSet.start();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void buildGoogleApiClient(boolean addAnother) {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        GoogleSignInOptions signInOptions = buildSignInOptions();
        if (!addAnother) {
            initializeGoogleService(signInOptions);
            signIn();
        } else {
            GoogleService.getGoogleApiClient().connect();
            GoogleService.getGoogleApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(@Nullable Bundle bundle) {
                    if (GoogleService.getInstance().getGoogleApiClient().isConnected()) {
                        Auth.GoogleSignInApi.signOut(GoogleService.getInstance().getGoogleApiClient()).setResultCallback((status) -> {
                            if (status.isSuccess()) {
                                initializeGoogleService(signInOptions);
                                signIn();
                            }
                        });
                    }
                }

                @Override
                public void onConnectionSuspended(int i) {

                }
            });
        }
    }

    public GoogleSignInOptions buildSignInOptions() {
        return new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar"))
                .requestEmail()
                .requestIdToken(CLIENT_ID)
                .requestServerAuthCode(CLIENT_ID)
                .build();
    }

    public void initializeGoogleService(GoogleSignInOptions signInOptions) {
        GoogleService.init(new GoogleApiClient
                .Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this, LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build());
    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(GoogleService.getInstance().getGoogleApiClient());
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
                    String googleAccountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                    if (googleAccountName != null) {
                        //buildGoogleApiClient(googleAccountName);
                    }
                }
                break;
        }
    }

    public void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            final String userName = account.getDisplayName();
            final String userEmail = account.getEmail();
            final String givenName = account.getGivenName();
            backendApi.postAuth(kalPref.clientToken(), new GoogleAuth(account.getServerAuthCode(), userEmail, userName)).enqueue(new Callback<KalUser>() {

                @Override
                public void onResponse(Call<KalUser> call, Response<KalUser> response) {
                    KalUser kalUser = response.body();
                    String accessToken = kalUser.getAccessToken();
                    String clientToken = kalUser.getClientToken();
                    editKalPref(userEmail, userName, accessToken, clientToken);
                    Log.d("shared", kalPref.getString(userEmail));
                    Intent signIn = displayNextActivity();
                    signIn.putExtra("userGivenName", givenName);
                    signIn.putExtra("googleAccountName", userEmail);
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

    private void editKalPref(String email, String userName, String accessToken, String clientToken) {
        kalPref.setClienttoken(clientToken);
        googleAuth = new GoogleAuth();
        if (!kalPref.getAccounts().contains(email)) {

            googleAuth.setEmail(email);
            googleAuth.setDisplayName(userName);
            googleAuth.setAccessToken(accessToken);

            kalPref.putAuth(googleAuth);

        }
        Toast.makeText(this, "Sorry, you can't add the account you are already logged into!", Toast.LENGTH_LONG).show();
    }

    public void onStop() {
        super.onStop();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    public Intent displayNextActivity() {
        if (getIntent().getBooleanExtra("ifNewAccChoosen", false)) {
            return new Intent(LoginActivity.this, MainActivity.class);
        } else {
            return new Intent(LoginActivity.this, WelcomeScreenActivity.class);
        }
    }
}
