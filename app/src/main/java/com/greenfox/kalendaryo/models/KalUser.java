package com.greenfox.kalendaryo.models;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

public class KalUser {

    private long userId;
    private String clientToken;
    private String userEmail;
    private String accessToken;

    public long getId() {
        return userId;
    }

    public void setId(long id) {
        this.userId = id;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;

    }
}
