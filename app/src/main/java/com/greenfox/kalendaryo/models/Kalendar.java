package com.greenfox.kalendaryo.models;

import com.greenfox.kalendaryo.adapter.GoogleCalendarAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bekob on 2018-01-17.
 */

public class Kalendar implements Serializable,GoogleCalendarAdapter.ListChange {

    String outputGoogleAuthId;
    List<String> inputGoogleCalendars;

    public Kalendar() {
        this.inputGoogleCalendars = new ArrayList<>();
    }

    public String getOutputGoogleAuthId() {
        return outputGoogleAuthId;
    }

    public void setOutputGoogleAuthId(String outputGoogleAuthId) {
        this.outputGoogleAuthId = outputGoogleAuthId;
    }

    public List<String> getInputGoogleCalendars() {
        return inputGoogleCalendars;
    }

    public void setInputGoogleCalendars(List<String> inputGoogleCalendars) {
        this.inputGoogleCalendars = inputGoogleCalendars;
    }

    @Override
    public void saveCalendar(String calendarTitle) {
        inputGoogleCalendars.add(calendarTitle);
    }

    @Override
    public void removeCalendar(String calendarTitle) {
        inputGoogleCalendars.remove(calendarTitle);
    }


}
