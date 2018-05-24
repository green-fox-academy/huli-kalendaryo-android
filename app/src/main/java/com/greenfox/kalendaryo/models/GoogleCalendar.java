package com.greenfox.kalendaryo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by barba on 04/01/2018.
 */

public class GoogleCalendar implements Parcelable {

    String id;
    String summary;

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
