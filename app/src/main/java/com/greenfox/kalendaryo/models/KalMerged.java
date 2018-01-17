package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by bekob on 2018-01-17.
 */

public class KalMerged {

    private long userId;
    private String userName;
    private String outputAccount;
    private String outputCalendarId;

    List<CalendarId> calendarId;

    public KalMerged(long userId, String userName, String outputAccount, String outputCalendarId, List<CalendarId> calendarId) {
        this.userId = userId;
        this.userName = userName;
        this.outputAccount = outputAccount;
        this.outputCalendarId = outputCalendarId;
        this.calendarId = calendarId;
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

    public List<CalendarId> getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(List<CalendarId> calendarId) {
        this.calendarId = calendarId;
    }
}
