package com.greenfox.kalendaryo.models.event;

import com.google.api.client.util.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Lilla on 2018. 03. 14..
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventsResource {

    String kind;
    String etag;
    String id;
    String status;
    DateTime created;
    DateTime updated;
    String summary;
    String colorId;
    EventAuth creator;
    EventAuth organizer;
    EventDate start;
    EventDate end;

}
