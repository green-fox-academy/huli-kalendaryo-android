package com.greenfox.kalendaryo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.greenfox.kalendaryo.models.event.EventResponse;
import com.greenfox.kalendaryo.models.event.GoogleEvent;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventAdapter extends ArrayAdapter<EventResponse> {

    private List<GoogleEvent> events;
    private Context context;

    public EventAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void addEvents(List<GoogleEvent> newEvents) {
        this.events.addAll(newEvents);
        //notifyDataSetChanged();
    }

    public void addEvent(GoogleEvent event) {
        this.events.add(event);
    }

}
