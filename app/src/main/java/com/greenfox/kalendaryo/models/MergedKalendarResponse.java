package com.greenfox.kalendaryo.models;

/**
 * Created by bekob on 2018-01-17.
 */

public class MergedKalendarResponse {
    String mergedCalendarId;
    String status;

    public MergedKalendarResponse(String mergedCalendarId, String status) {
        this.mergedCalendarId = mergedCalendarId;
        this.status = status;
    }

    public MergedKalendarResponse() {
    }

    public String getMergedCalendarId() {
        return mergedCalendarId;
    }

    public void setMergedCalendarId(String mergedCalendarId) {
        this.mergedCalendarId = mergedCalendarId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
