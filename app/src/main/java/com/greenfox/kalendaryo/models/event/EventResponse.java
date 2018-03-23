package com.greenfox.kalendaryo.models.event;

import android.util.EventLogTags;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.api.client.util.DateTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    List<WeekViewEvent> items;

}
