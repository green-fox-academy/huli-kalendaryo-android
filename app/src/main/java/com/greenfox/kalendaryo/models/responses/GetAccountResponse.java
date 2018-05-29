package com.greenfox.kalendaryo.models.responses;

import com.greenfox.kalendaryo.models.GoogleAuth;

import java.util.List;

public class GetAccountResponse {

    private long id;
    private String userEmail;
    List<GoogleAuth> googleAuths;

    public GetAccountResponse() {
    }

    public GetAccountResponse(long id, String userEmail, List<GoogleAuth> googleAuths) {
        this.id = id;
        this.userEmail = userEmail;
        this.googleAuths = googleAuths;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<GoogleAuth> getGoogleAuths() {
        return googleAuths;
    }

    public void setGoogleAuths(List<GoogleAuth> googleAuths) {
        this.googleAuths = googleAuths;
    }
}
