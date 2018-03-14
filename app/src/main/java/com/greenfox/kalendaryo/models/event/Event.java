package com.greenfox.kalendaryo.models.event;

/**
 * Created by Lilla on 2018. 03. 14..
 */

public class Event {

    private String calendarId;
    private String eventId;

    public Event() {
    }

    public Event(String calendarId, String eventId) {
        this.calendarId = calendarId;
        this.eventId = eventId;
    }
}
