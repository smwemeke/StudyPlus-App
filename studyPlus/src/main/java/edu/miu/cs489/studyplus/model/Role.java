package edu.miu.cs489.studyplus.model;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public enum Role {
    COORDINATOR(
            Set.of(Permission.COORDINATOR_WRITE,
                    Permission.COORDINATOR_READ)
    ),
    PARTICIPANT(
            Set.of(
                    Permission.PARTICIPANT_WRITE,
                    Permission.PARTICIPANT_READ
            )
    );
    private final Set<Permission> permissions;


}
