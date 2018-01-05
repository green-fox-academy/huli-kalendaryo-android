package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by barba on 04/01/2018.
 */

public class KalendarsResponse {

     String kind;
     String etag;
     String nextPageToken;
     String nextSyncToken;
     List<Kalendar> items;

    public KalendarsResponse(String etag, String nextPageToken, String nextSyncToken, List<Kalendar> items) {
        this.kind = "calendar#calendarList";
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.nextSyncToken = nextSyncToken;
        this.items = items;
    }

    public KalendarsResponse() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getNextSyncToken() {
        return nextSyncToken;
    }

    public void setNextSyncToken(String nextSyncToken) {
        this.nextSyncToken = nextSyncToken;
    }

    public List<Kalendar> getItems() {
        return items;
    }

    public void setItems(List<Kalendar> items) {
        this.items = items;
    }
}
