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

}
