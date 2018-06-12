package com.greenfox.kalendaryo.models.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.alamkanak.weekview.WeekViewEvent;

import java.io.Serializable;
import java.util.List;

public class PreviewEvent implements Serializable{

    private String id;
    private String created;
    private String updated;
    private String summary;
    private StartTime start;
    private EndTime end;

    public PreviewEvent() {
    }

    public PreviewEvent(String id, String created, String updated, String summary, StartTime start, EndTime end) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.summary = summary;
        this.start = start;
        this.end = end;
    }

    protected PreviewEvent(Parcel in) {
        id = in.readString();
        created = in.readString();
        updated = in.readString();
        summary = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public StartTime getStart() {
        return start;
    }

    public void setStart(StartTime start) {
        this.start = start;
    }

    public EndTime getEnd() {
        return end;
    }

    public void setEnd(EndTime end) {
        this.end = end;
    }
}
