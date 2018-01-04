package com.greenfox.kalendaryo.models;

import java.util.List;

/**
 * Created by barba on 04/01/2018.
 */

public class Kalendar {

    String accessRole;
    String conferenceProperties;
    List<String> defaultReminders;
    boolean deleted;
    String description;
    String etag;
    boolean hidden;
    String id;
    String kind;
    String location;
    String notificationSettings;
    boolean primary;
    boolean selected;
    String summary;
    String timeZone;

    public Kalendar() {
    }

    public Kalendar(String accessRole, String conferenceProperties, List<String> defaultReminders, boolean deleted, String description, String etag, boolean hidden, String id, String kind, String location, String notificationSettings, boolean primary, boolean selected, String summary, String timeZone) {
        this.accessRole = accessRole;
        this.conferenceProperties = conferenceProperties;
        this.defaultReminders = defaultReminders;
        this.deleted = deleted;
        this.description = description;
        this.etag = etag;
        this.hidden = hidden;
        this.id = id;
        this.kind = kind;
        this.location = location;
        this.notificationSettings = notificationSettings;
        this.primary = primary;
        this.selected = selected;
        this.summary = summary;
        this.timeZone = timeZone;
    }

    public String getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
    }

    public String getConferenceProperties() {
        return conferenceProperties;
    }

    public void setConferenceProperties(String conferenceProperties) {
        this.conferenceProperties = conferenceProperties;
    }

    public List<String> getDefaultReminders() {
        return defaultReminders;
    }

    public void setDefaultReminders(List<String> defaultReminders) {
        this.defaultReminders = defaultReminders;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotificationSettings() {
        return notificationSettings;
    }

    public void setNotificationSettings(String notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
