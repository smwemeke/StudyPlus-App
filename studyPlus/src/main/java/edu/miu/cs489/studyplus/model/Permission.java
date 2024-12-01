package edu.miu.cs489.studyplus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    COORDINATOR_READ("coordinator:read"),
    COORDINATOR_WRITE("coordinator:write"),

    PARTICIPANT_READ("participant:read"),
    PARTICIPANT_WRITE("participant:write");

    private final String Permission;
}