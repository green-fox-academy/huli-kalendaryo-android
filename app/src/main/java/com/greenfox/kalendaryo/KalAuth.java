package com.greenfox.kalendaryo;

/**
 * Created by Ezzo on 2017. 12. 15..
 */

class KalAuth {
    public String auth_token;
    public String useremail;

    public KalAuth(String auth_token, String useremail) {
        this.auth_token = auth_token;
        this.useremail = useremail;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
