package com.greenfox.kalendaryo.models.event;

import com.google.api.client.util.DateTime;

import java.util.Date;

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
public class EventDate {

    Date date;
    DateTime dateTime;
    String timeZone;

}
