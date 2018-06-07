package com.greenfox.kalendaryo.models.event;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.api.client.util.DateTime;

import java.util.Collection;
import java.util.List;

public class EventResponse {

    public String kind;
    public String etag;
    public String summary;
    public String description;
    public String updated;
    public String timeZone;
    public String accessRole;
    public String nextPageToken;
    public String nextSyncToken;
    public List<PreviewEvent> items;

    public EventResponse() {
    }

    public EventResponse(String kind, String etag, String summary, String description,
                         String updated, String timeZone, String accessRole,
                          String nextPageToken,
                         String nextSyncToken, List<PreviewEvent> items) {
        this.kind = kind;
        this.etag = etag;
        this.summary = summary;
        this.description = description;
        this.updated = updated;
        this.timeZone = timeZone;
        this.accessRole = accessRole;
        this.nextPageToken = nextPageToken;
        this.nextSyncToken = nextSyncToken;
        this.items = items;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
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

    public List<PreviewEvent> getItems() {
        return items;
    }

    public void setItems(List<PreviewEvent> items) {
        this.items = items;
    }
}
