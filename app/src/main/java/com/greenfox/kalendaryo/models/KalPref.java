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
    private ArrayList<String> accounts;
    private Gson gson = new Gson();
    static final String CLIENTTOKEN = "clienttoken";


    public KalPref(Context context) {
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String list = this.sharedPref.getString("accountslist", "");
        if (list.equals("")) {
            this.accounts = new ArrayList<>();
        } else {
            this.accounts = gson.fromJson(list, ArrayList.class);
        }
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return this.sharedPref.getString(key, "");
    }

    public void putAuth(GoogleAuth googleAuth) {
        String value = gson.toJson(googleAuth);
        this.putString(googleAuth.getEmail(), value);
        addAccount(googleAuth.getEmail());
    }

    public GoogleAuth getAuth(String key) {
        String value = this.getString(key);
        GoogleAuth googleAuth = gson.fromJson(value, GoogleAuth.class);
        return googleAuth;
    }

    private void addAccount(String accountname) {
        accounts.add(accountname);
        String value = gson.toJson(accounts);
        this.putString("accountslist", value);
    }

    public void removeAccount(String key) {
        accounts.remove(key);
        String value = gson.toJson(accounts);
        this.putString("accountslist", value);
    }

    public ArrayList<String> getAccounts() {
        return this.accounts;
    }

    public void clearAccountsAndAll() {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        for (int i = 0; i < accounts.size(); i++) {
            editor.remove(accounts.get(i));
        }
        editor.clear();
        editor.apply();
        this.accounts = new ArrayList<>();
    }

    public List<GoogleAuth> getGoogleAuths() {

        ArrayList<String> accountNameList = this.getAccounts();
        ArrayList<GoogleAuth> auths = new ArrayList<>();

        for (int i = 0; i < accountNameList.size(); i++) {
            GoogleAuth auth = this.getAuth(accountNameList.get(i));
            auths.add(auth);
        }
        return auths;
    }

    public String clientToken() {
        return this.getString(CLIENTTOKEN);
    }

    public void setClienttoken(String value) {
        this.putString(CLIENTTOKEN, value);
    }

    public boolean isUserSignedIn () {
        return !clientToken().equals("");
    }
}
