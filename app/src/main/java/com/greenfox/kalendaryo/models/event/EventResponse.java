package com.greenfox.kalendaryo.models.event;

import android.provider.CalendarContract;
import android.util.EventLogTags;

import com.google.api.client.util.DateTime;

import java.util.List;

public class EventResponse {

    public String kind;
    public EventLogTags etag;
    public String summary;
    public String description;
    public DateTime updated;
    public String timeZone;
    public String accessRole;
    public String nextPageToken;
    public String nextSyncToken;
    public List<CalendarContract.Events> items;

    public EventResponse() {
    }

    public EventResponse(String kind, EventLogTags etag, String summary, String description,
                         DateTime updated, String timeZone, String accessRole,
                          String nextPageToken,
                         String nextSyncToken, List<CalendarContract.Events> items) {
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
}
