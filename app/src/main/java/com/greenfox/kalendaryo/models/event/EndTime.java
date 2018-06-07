package com.greenfox.kalendaryo.models.event;

public class EndTime {

    private String date;
    private String dateTime;
    private String timeZone;

    public EndTime() {
    }

    public EndTime(String date, String dateTime, String timeZone) {
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
