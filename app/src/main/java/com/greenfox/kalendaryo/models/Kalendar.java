package com.greenfox.kalendaryo.models;

import com.greenfox.kalendaryo.adapter.GoogleCalendarAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Kalendar implements Serializable,GoogleCalendarAdapter.ListChange {

    String outputGoogleAuthId;
    List<GoogleCalendar> inputGoogleCalendars;
    String customName;
    private String id;

    public Kalendar() {
        this.inputGoogleCalendars = new ArrayList<>();
    }

    public String getOutputGoogleAuthId() {
        return outputGoogleAuthId;
    }

    public void setOutputGoogleAuthId(String outputGoogleAuthId) {
        this.outputGoogleAuthId = outputGoogleAuthId;
    }

    public List<GoogleCalendar> getInputGoogleCalendars() {
        return inputGoogleCalendars;
    }

    public void setInputGoogleCalendars(List<GoogleCalendar> inputGoogleCalendars) {
        this.inputGoogleCalendars = inputGoogleCalendars;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public void saveCalendar(GoogleCalendar calendarTitle) {
        inputGoogleCalendars.add(calendarTitle);
    }

    @Override
    public void removeCalendar(GoogleCalendar calendarTitle) {
        inputGoogleCalendars.remove(calendarTitle);
    }


    public String getId() {
        return id;
    }
}
