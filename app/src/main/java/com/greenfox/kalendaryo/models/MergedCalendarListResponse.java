package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class MergedCalendarListResponse {
    private List<MergedCalendarResponse> mergedCalendars;

    public MergedCalendarListResponse() {
    }

    public MergedCalendarListResponse(List<MergedCalendarResponse> mergedCalendars) {
        this.mergedCalendars = mergedCalendars;
    }

    public List<MergedCalendarResponse> getMergedCalendars() {
        return mergedCalendars;
    }

    public void setMergedCalendars(List<MergedCalendarResponse> mergedCalendars) {
        this.mergedCalendars = mergedCalendars;
    }
}
