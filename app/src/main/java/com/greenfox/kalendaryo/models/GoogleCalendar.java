package com.greenfox.kalendaryo.models;

/**
 * Created by barba on 04/01/2018.
 */

public class GoogleCalendar {

    String id;
    String summary;

    public GoogleCalendar(String id, String summary) {
        this.id = id;
        this.summary = summary;
    }

    public GoogleCalendar() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
