package edu.miu.cs489.studyplus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    COORDINATOR_READ("coord:read"),
    COORDINATOR_WRITE("coord:write"),

    PARTICIPANT_READ("part:read"),
    PARTICIPANT_WRITE("part:write");

    private final String permission;
}