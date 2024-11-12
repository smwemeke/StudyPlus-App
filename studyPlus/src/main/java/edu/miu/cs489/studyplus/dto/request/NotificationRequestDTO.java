package edu.miu.cs489.studyplus.dto.request;

import java.sql.Timestamp;

public record NotificationRequestDTO(
        StudyRequestDTO studyRequestDTO,
        ParticipantRequestDTO participantRequestDTO,
        String subject,
        String message,
        Timestamp sentAt
) {
}
