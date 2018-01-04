package com.greenfox.kalendaryo.models;

/**
 * Created by barba on 04/01/2018.
 */

public class Kalendar {

    String id;
    String summary;

    public Kalendar(String id, String summary) {
        this.id = id;
        this.summary = summary;
    }

    public Kalendar() {
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
