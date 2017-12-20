package com.greenfox.kalendaryo;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

class KalUser {
    public long id;
    public String accessToken;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String clientToken) {
        this.accessToken = clientToken;
    }
}
