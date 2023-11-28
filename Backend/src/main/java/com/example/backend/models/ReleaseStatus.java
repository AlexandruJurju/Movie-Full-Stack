package com.example.backend.models;

import io.swagger.v3.oas.annotations.media.Schema;

// TODO: fix enum for release status
public enum ReleaseStatus {
    RELEASED("released"),
    UPCOMING("upcoming"),
    UNKNOWN("other");

    private String value;

    ReleaseStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ReleaseStatus{" +
                "value='" + value + '\'' +
                '}';
    }
}
