package com.example.backend.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum ReleaseStatus {
    RELEASED,
    UPCOMING,
    UNKNOWN;
}
