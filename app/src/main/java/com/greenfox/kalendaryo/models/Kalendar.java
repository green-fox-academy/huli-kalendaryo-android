package com.greenfox.kalendaryo.models;

import com.greenfox.kalendaryo.adapter.GoogleCalendarAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bekob on 2018-01-17.
 */

public class Kalendar implements Serializable,GoogleCalendarAdapter.ListChange {

    String outputCalendarId;
    List<String> inputCalendarIds;

    public Kalendar() {
        this.inputCalendarIds = new ArrayList<>();
    }

    public String getOutputCalendarId() {
        return outputCalendarId;
    }

    public void setOutputCalendarId(String outputCalendarId) {
        this.outputCalendarId = outputCalendarId;
    }

    public List<String> getInputCalendarIds() {
        return inputCalendarIds;
    }

    public void setInputCalendarIds(List<String> inputCalendarIds) {
        this.inputCalendarIds = inputCalendarIds;
    }

    @Override
    public void saveCalendar(String calendarTitle) {
        inputCalendarIds.add(calendarTitle);
    }

    @Override
    public void removeCalendar(String calendarTitle) {
        inputCalendarIds.remove(calendarTitle);
    }
}
