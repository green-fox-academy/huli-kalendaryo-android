package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by bekob on 2018-01-17.
 */

public class KalMerged {

    String outputCalendarId;
    List<String> inputCalendarIds;

    public KalMerged(String outputCalendarId, List<String> inputCalendarIds) {
        this.outputCalendarId = outputCalendarId;
        this.inputCalendarIds = inputCalendarIds;
    }

    public KalMerged() {
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
