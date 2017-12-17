package com.greenfox.kalendaryo;

/**
 * Created by lica on 2017. 12. 15..
 */

public class UserData {
    String email;
    String accessToken;

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

    @Override
    public String toString() {
        return "UserData{" +
                "email='" + email + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
