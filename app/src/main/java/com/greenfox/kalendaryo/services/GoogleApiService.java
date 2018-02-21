package com.greenfox.kalendaryo.services;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Szilvi on 2018. 01. 02..
 */

public class GoogleApiService {

    private static GoogleApiClient googleApiClient = null;
    private static GoogleApiService singleton = null;

    protected GoogleApiService(GoogleApiClient googleApiClient) {

        this.googleApiClient = googleApiClient;
    }

    public static GoogleApiService getInstance() {
        if(singleton == null) {
            throw new AssertionError("You have to call init first");
        }
        return singleton;
    }

    public synchronized static GoogleApiService init(GoogleApiClient googleApiClient) {
        singleton = new GoogleApiService(googleApiClient);
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
