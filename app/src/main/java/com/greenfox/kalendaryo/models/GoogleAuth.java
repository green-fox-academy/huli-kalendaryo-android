package com.greenfox.kalendaryo.models;


/**
 * Created by Ezzo on 2017. 12. 15..
 */

public class GoogleAuth {

    private String authCode;
    private String email;
    private String displayName;
    private String accessToken;

    public GoogleAuth(String authCode, String email, String displayName) {
        this.authCode = authCode;
        this.email = email;
        this.displayName = displayName;
    }

    public GoogleAuth() {
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
