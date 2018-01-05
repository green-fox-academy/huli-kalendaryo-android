package com.greenfox.kalendaryo.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lica on 2018. 01. 04..
 */

public class KalPref {
    private SharedPreferences sharedPref;
    private List<String> accounts;


    public KalPref(Context context) {
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public void putSting(String key, String value) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return this.sharedPref.getString(key, "");
    }

    public void putAuth(String key, KalAuth kalAuth) {
        Gson gson = new Gson();
        String value = gson.toJson(kalAuth);
        this.putSting(key, value);
    }

    public KalAuth getAuth(String key) {
        Gson gson = new Gson();
        String value = this.getString(key);
        KalAuth kalAuth = gson.fromJson(value, KalAuth.class);
        return kalAuth;
    }

    

}
