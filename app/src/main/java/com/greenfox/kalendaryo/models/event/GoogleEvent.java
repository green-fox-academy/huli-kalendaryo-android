package com.greenfox.kalendaryo.models.event;

import android.util.EventLogTags;

import com.google.api.client.util.DateTime;
import com.greenfox.kalendaryo.models.KalAuth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoogleEvent {

    String kind;
    EventLogTags etag;
    String id;
    String status;
    DateTime created;
    DateTime updated;
    String summary;
    String colorId;
    KalAuth creator;
    KalAuth organizer;
    EventDate start;
    EventDate end;

}