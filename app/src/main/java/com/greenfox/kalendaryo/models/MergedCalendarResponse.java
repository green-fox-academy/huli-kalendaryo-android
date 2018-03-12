package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class MergedCalendarResponse {
    private String outputAccountId;
    private String outputCalendarId;
    private List<String> inputCalendarIds;

    public MergedCalendarResponse() {
    }

    public MergedCalendarResponse(String outputAccountId, String outputCalendarId) {
        this.outputAccountId = outputAccountId;
        this.outputCalendarId = outputCalendarId;
    }

    public MergedCalendarResponse(String outputAccountId, String outputCalendarId, List<String> inputCalendarIds) {
        this.outputAccountId = outputAccountId;
        this.outputCalendarId = outputCalendarId;
        this.inputCalendarIds = inputCalendarIds;
    }

    public String getOutputAccountId() {
        return outputAccountId;
    }

    public void setOutputAccountId(String outputAccountId) {
        this.outputAccountId = outputAccountId;
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
}
