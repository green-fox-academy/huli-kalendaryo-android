package com.greenfox.kalendaryo.models.responses;

import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class GetKalendarResponse {
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
