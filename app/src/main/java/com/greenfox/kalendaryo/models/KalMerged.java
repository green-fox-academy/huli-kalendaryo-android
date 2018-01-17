package com.greenfox.kalendaryo.models;

/**
 * Created by bekob on 2018-01-17.
 */

public class KalMerged {

    private long userId;
    private String userName;
    private String outputAccount;
    private String outputCalendarId;

    public KalMerged(long userId, String userName, String outputAccount, String outputCalendarId) {
        this.userId = userId;
        this.userName = userName;
        this.outputAccount = outputAccount;
        this.outputCalendarId = outputCalendarId;
    }

    public KalMerged() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOutputAccount() {
        return outputAccount;
    }

    public void setOutputAccount(String outputAccount) {
        this.outputAccount = outputAccount;
    }

    public String getOutputCalendarId() {
        return outputCalendarId;
    }

    public void setOutputCalendarId(String outputCalendarId) {
        this.outputCalendarId = outputCalendarId;
    }
}
