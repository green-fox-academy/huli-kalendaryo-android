package com.greenfox.kalendaryo.models;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public class KalAuth {
    public String authCode;
    public String userEmail;
    public String userName;

    public KalAuth(String authToken, String userEmail, String userName) {
        this.authCode = authToken;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getAuthToken() {
        return authCode;
    }

    public void setAuthToken(String authToken) {
        this.authCode = authToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
