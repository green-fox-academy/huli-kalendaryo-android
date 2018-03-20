package com.greenfox.kalendaryo.models;

import com.greenfox.kalendaryo.adapter.KalendarAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bekob on 2018-01-17.
 */

public class KalMerged implements Serializable,KalendarAdapter.ListChange {

    String outputCalendarId;
    List<CalendarId> inputCalendarIds;

    public KalMerged() {
        this.inputCalendarIds = new ArrayList<>();
    }

    public String getOutputCalendarId() {
        return outputCalendarId;
    }

    public void setOutputCalendarId(String outputCalendarId) {
        this.outputCalendarId = outputCalendarId;
    }

    public List<CalendarId> getInputCalendarIds() {
        return inputCalendarIds;
    }

    public void setInputCalendarIds(List<CalendarId> inputCalendarIds) {
        this.inputCalendarIds = inputCalendarIds;
    }

    @Override
    public void saveCalendar(String calendarTitle) {
        inputCalendarIds.add(new CalendarId(calendarTitle));
    }

    @Override
    public void removeCalendar(String calendarTitle) {
        inputCalendarIds.remove(calendarTitle);
    }
}
