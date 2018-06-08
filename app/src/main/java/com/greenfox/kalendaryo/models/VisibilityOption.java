package com.greenfox.kalendaryo.models;

public enum VisibilityOption {
    DEFAULT("Default visibility"),
    PUBLIC("Public"),
    PRIVATE("Private");

    private String visibilities;

    VisibilityOption(String visibility) {
        this.visibilities = visibility;
    }

    @Override
    public String toString() {
        return visibilities;
    }
}
