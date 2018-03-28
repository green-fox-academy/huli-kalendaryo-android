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
        this.title = false;
        this.organizer = false;
        this.location = false;
        this.attendants = false;
        this.description = false;
    }

    public SharingOptions(String calendarName) {

        this.calendarName = calendarName;
        this.title = false;
        this.organizer = false;
        this.location = false;
        this.attendants = false;
        this.description = false;
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

    public void setOption(String option){
        if (option.equals("check_title")) {
            setTitle(true);
        } else if (option.equals("check_attendants")) {
            setAttendants(true);
        } else if (option.equals("check_description")) {
            setDescription(true);
        } else if (option.equals("check_location")) {
            setLocation(true);
        } else if (option.equals("check_organizer")) {
            setOrganizer(true);
        }
    }

    public void removeOption(String option){
        if (option.equals("check_title")) {
            setTitle(false);
        } else if (option.equals("check_attendants")) {
            setAttendants(false);
        } else if (option.equals("check_description")) {
            setDescription(false);
        } else if (option.equals("check_location")) {
            setLocation(false);
        } else if (option.equals("check_organizer")) {
            setOrganizer(false);
        }
    }
}

