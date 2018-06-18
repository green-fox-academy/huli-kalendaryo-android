package com.greenfox.kalendaryo.models;

public enum VisibilityOption {
    DEFAULT("default"),
    PUBLIC("public"),
    PRIVATE("private");

    private String visibilities;

    VisibilityOption(String visibility) {
        this.visibilities = visibility;
    }

    @Override
    public String toString() {
        return visibilities;
    }
}
