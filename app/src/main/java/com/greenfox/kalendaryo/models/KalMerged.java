package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by bekob on 2018-01-17.
 */

public class KalMerged {

    private String outputAccount;

    List<String> calendarId;

    public KalMerged(List<String> calendarId, String outputAccount) {
        this.outputAccount = outputAccount;
        this.calendarId = calendarId;
    }

    public KalMerged() {
    }

    public String getOutputAccount() {
        return outputAccount;
    }

    public void setOutputAccount(String outputAccount) {
        this.outputAccount = outputAccount;
    }

    public List<String> getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(List<String> calendarId) {
        this.calendarId = calendarId;
    }
}
