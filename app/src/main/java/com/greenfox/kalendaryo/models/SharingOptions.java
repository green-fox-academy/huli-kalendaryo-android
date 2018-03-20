package com.greenfox.kalendaryo.models;

import java.io.Serializable;

/**
 * Created by Máthé Boglárka on 2018. 03. 14..
 */

public class SharingOptions implements Serializable {

    String calendarName;
    Boolean title;
    Boolean organizer;
    Boolean location;
    Boolean attendants;
    Boolean description;

    public SharingOptions() {
    }

    public SharingOptions(String calendarName) {
        this.calendarName = calendarName;
    }

    public SharingOptions(String calendarName, Boolean title, Boolean organizer, Boolean location, Boolean attendants, Boolean description) {
        this.calendarName = calendarName;
        this.title = title;
        this.organizer = organizer;
        this.location = location;
        this.attendants = attendants;
        this.description = description;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }

    public Boolean getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Boolean organizer) {
        this.organizer = organizer;
    }

    public Boolean getLocation() {
        return location;
    }

    public void setLocation(Boolean location) {
        this.location = location;
    }

    public Boolean getAttendants() {
        return attendants;
    }

    public void setAttendants(Boolean attendants) {
        this.attendants = attendants;
    }

    public Boolean getDescription() {
        return description;
    }

    public void setDescription(Boolean description) {
        this.description = description;
    }
}
