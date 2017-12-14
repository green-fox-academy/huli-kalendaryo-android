package com.greenfox.kalendaryo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by Ezzo on 2017. 12. 14..
 */

public class SignOut extends AppCompatActivity implements View.OnClickListener {


    private GoogleSignInClient mGoogleSignInClient;

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // ...
            case R.id.button_sign_out:
                signOut();
                break;
            // ...
        }
    }
}
