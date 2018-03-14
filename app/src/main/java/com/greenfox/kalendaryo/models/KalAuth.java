package com.greenfox.kalendaryo.models;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public class KalAuth {

    private String authCode;
    private String email;
    private String displayName;
    private String accessToken;


    public KalAuth(String authCode, String email, String displayName) {
        this.authCode = authCode;
        this.email = email;
        this.displayName = displayName;
    }

    @Inject
    public KalAuth() {
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
