package com.greenfox.kalendaryo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by barba on 04/01/2018.
 */

public class Kalendar implements Parcelable {

    String id;
    String summary;

    public Kalendar(String id, String summary) {
        this.id = id;
        this.summary = summary;
    }

    public Kalendar() {
    }

    public Kalendar(Parcel in) {
        id = in.readString();
        summary = in.readString();
    }

    public static final Creator<Kalendar> CREATOR = new Creator<Kalendar>() {
        @Override
        public Kalendar createFromParcel(Parcel in) {
            return new Kalendar(in);
        }

        @Override
        public Kalendar[] newArray(int size) {
            return new Kalendar[size];
        }
    };

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
