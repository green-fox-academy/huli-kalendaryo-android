package com.greenfox.kalendaryo.models.event;

import android.provider.CalendarContract;
import android.util.EventLogTags;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.api.client.util.DateTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EventResponse {

    String kind;
    EventLogTags etag;
    String summary;
    String description;
    DateTime updated;
    String timeZone;
    String accessRole;
    List<EventReminder> defaultReminder;
    String nextPageToken;
    String nextSyncToken;
    List<CalendarContract.Events> items;

    public EventResponse() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public EventLogTags getEtag() {
        return etag;
    }

    public void setEtag(EventLogTags etag) {
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

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
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

    public List<EventReminder> getDefaultReminder() {
        return defaultReminder;
    }

    public void setDefaultReminder(List<EventReminder> defaultReminder) {
        this.defaultReminder = defaultReminder;
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

    public List<CalendarContract.Events> getItems() {
        return items;
    }

    public void setItems(List<CalendarContract.Events> items) {
        this.items = items;
    }
}
