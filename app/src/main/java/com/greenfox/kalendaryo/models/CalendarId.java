package com.greenfox.kalendaryo.models;

import java.io.Serializable;

/**
 * Created by bekob on 2018-01-17.
 */

public class CalendarId implements Serializable {

    private String id;
    KalAuth kalAuth;
    KalMerged kalMerged;
    SharingOptions sharingOptions;

    public CalendarId() {
    }

    public CalendarId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KalAuth getKalAuth() {
        return kalAuth;
    }

    public void setKalAuth(KalAuth kalAuth) {
        this.kalAuth = kalAuth;
    }

    public KalMerged getKalMerged() {
        return kalMerged;
    }

    public void setKalMerged(KalMerged kalMerged) {
        this.kalMerged = kalMerged;
    }

    public SharingOptions getSharingOptions() {
        return sharingOptions;
    }

    public void setSharingOptions(SharingOptions sharingOptions) {
        this.sharingOptions = sharingOptions;
    }
}
