package com.greenfox.kalendaryo.models.event;

import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

public class EndTime {

    private String date;
    private Date dateTime;
    private TimeZone timeZone;

    public EndTime() {
    }

    public EndTime(String date, Date dateTime, TimeZone timeZone) {
        this.date = date;
        this.dateTime = dateTime;
        this.timeZone = timeZone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
