package com.greenfox.kalendaryo.models.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class GetKalendarResponse implements Serializable{
    private long id;
    private String outputGoogleAuthId;
    private String outputCalendarId;
    private List<String> inputGoogleCalendars;

    public GetKalendarResponse() {
    }

    public GetKalendarResponse(String outputGoogleAuthId, String outputCalendarId) {
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.outputCalendarId = outputCalendarId;
    }

    public GetKalendarResponse(String outputGoogleAuthId, String outputCalendarId, List<String> inputGoogleCalendars) {
        this.outputGoogleAuthId = outputGoogleAuthId;
        this.outputCalendarId = outputCalendarId;
        this.inputGoogleCalendars = inputGoogleCalendars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOutputGoogleAuthId() {
        return outputGoogleAuthId;
    }

    public void setOutputGoogleAuthId(String outputGoogleAuthId) {
        this.outputGoogleAuthId = outputGoogleAuthId;
    }

    public String getOutputCalendarId() {
        return outputCalendarId;
    }

    public void setOutputCalendarId(String outputCalendarId) {
        this.outputCalendarId = outputCalendarId;
    }

    public List<String> getInputGoogleCalendars() {
        return inputGoogleCalendars;
    }

    public void setInputGoogleCalendars(List<String> inputGoogleCalendars) {
        this.inputGoogleCalendars = inputGoogleCalendars;
    }
}
