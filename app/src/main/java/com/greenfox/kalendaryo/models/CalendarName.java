package com.greenfox.kalendaryo.models;

import com.google.api.services.calendar.model.Calendar;

/**
 * Created by barba on 02/01/2018.
 */

public class CalendarName {

    Calendar calendar = new Calendar();
    String calendarName;

    public CalendarName(String calendarName) {
        this.calendarName = calendar.getDescription();
    }

    public CalendarName() {
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
}
