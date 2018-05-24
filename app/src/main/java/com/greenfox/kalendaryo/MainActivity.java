package com.greenfox.kalendaryo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.greenfox.kalendaryo.adapter.PagerAdapter;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.services.GoogleService;

public class MainActivity extends AppCompatActivity {

    KalPref kalPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        kalPref = new KalPref(this.getApplicationContext());
        if (!kalPref.isUserSignedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_tab_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Kalendars"));
        tabLayout.addTab(tabLayout.newTab().setText("Accounts"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.log_out) {
            logOut();
        }
        return true;
    }

    public void logOut() {

        GoogleService.getInstance().getGoogleApiClient().connect();
        GoogleService.getInstance().getGoogleApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                if(GoogleService.getInstance().getGoogleApiClient().isConnected()) {
                    Auth.GoogleSignInApi.signOut(GoogleService.getInstance().getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.d("Log out", "User Logged out");
                                Toast.makeText(getApplicationContext(), "You successfully logged out", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                }
                            }
                    });
                    }
                }
            @Override
            public void onConnectionSuspended(int i) {
                Log.d("Connection suspended", "Google API Client Connection Suspended");
                }
        });
        kalPref.clearAccountsAndAll();
    }
}
