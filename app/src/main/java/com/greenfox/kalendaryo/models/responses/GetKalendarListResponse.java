package com.greenfox.kalendaryo.models.responses;

import java.util.List;


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
