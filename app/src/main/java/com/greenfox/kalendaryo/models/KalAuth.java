package com.greenfox.kalendaryo.models;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public class KalAuth {
<<<<<<< HEAD
=======
    public String authCode;
    public String userEmail;
    public String userName;


    public KalAuth(String authToken, String userEmail, String userName) {
        this.authCode = authToken;
        this.userEmail = userEmail;
        this.userName = userName;
    }
>>>>>>> 5c29261f218d04c15c6512a33d36238018cb5862

    private String authCode;
    private String email;
    private String displayName;

    public KalAuth(String authCode, String useremail, String displayName) {
        this.authCode = authCode;
        this.email = useremail;
        this.displayName = displayName;
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
}
