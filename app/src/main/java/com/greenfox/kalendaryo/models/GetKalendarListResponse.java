package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class GetKalendarListResponse {
    private List<GetKalendarResponse> kalendars;

    public GetKalendarListResponse() {
    }

    public GetKalendarListResponse(List<GetKalendarResponse> kalendars) {
        this.kalendars = kalendars;
    }

    public List<GetKalendarResponse> getKalendars() {
        return kalendars;
    }

    public void setKalendars(List<GetKalendarResponse> kalendars) {
        this.kalendars = kalendars;
    }
}
