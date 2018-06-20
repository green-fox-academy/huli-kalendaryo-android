package com.greenfox.kalendaryo.models.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.client.util.DateTime;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

public class StartTime implements Serializable{

    private String date;
    private Date dateTime;
    private TimeZone timeZone;

    public StartTime() {
    }

    public StartTime(String date, Date dateTime, TimeZone timeZone) {
        this.date = date;
        this.dateTime = dateTime;
        this.timeZone = timeZone;
    }

    protected StartTime(Parcel in) {
        date = in.readString();
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
