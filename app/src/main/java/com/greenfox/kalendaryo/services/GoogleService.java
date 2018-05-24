package com.greenfox.kalendaryo.services;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Szilvi on 2018. 01. 02..
 */

public class GoogleService {

    private static GoogleApiClient googleApiClient = null;
    private static GoogleService singleton = null;

    protected GoogleService(GoogleApiClient googleApiClient) {

        this.googleApiClient = googleApiClient;
    }

    public static GoogleService getInstance() {
        if(singleton == null) {
            throw new AssertionError("You have to call init first");
        }
        return singleton;
    }

    public synchronized static GoogleService init(GoogleApiClient googleApiClient) {
        singleton = new GoogleService(googleApiClient);
        return singleton;
    }

    public static void finish(){
        singleton = null;
        googleApiClient = null;
    }


    public static GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }


}
