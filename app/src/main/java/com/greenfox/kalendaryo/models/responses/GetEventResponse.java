package com.greenfox.kalendaryo.models.responses;

import com.greenfox.kalendaryo.models.event.PreviewEvent;

import java.util.List;

public class GetEventResponse {

    List<PreviewEvent> events;

    public GetEventResponse() {
    }

    public GetEventResponse(List<PreviewEvent> events) {
        this.events = events;
    }

    public List<PreviewEvent> getEvents() {
        return events;
    }

    public void setEvents(List<PreviewEvent> events) {
        this.events = events;
    }
}
