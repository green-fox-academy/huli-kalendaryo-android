package com.greenfox.kalendaryo.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GoogleCalendar implements Parcelable, Serializable {

    String id;
    String summary;
    VisibilityOption sharingOption;
    private String email;

    public GoogleCalendar() {
    }

    public GoogleCalendar(Parcel in) {
        id = in.readString();
        summary = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public VisibilityOption getSharingOption() {
        return sharingOption;
    }

    public void setSharingOption(VisibilityOption sharingOption) {
        this.sharingOption = sharingOption;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final Creator<GoogleCalendar> CREATOR = new Creator<GoogleCalendar>() {
        @Override
        public GoogleCalendar createFromParcel(Parcel in) {
            return new GoogleCalendar(in);
        }

        @Override
        public GoogleCalendar[] newArray(int size) {
            return new GoogleCalendar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(summary);
    }
}
