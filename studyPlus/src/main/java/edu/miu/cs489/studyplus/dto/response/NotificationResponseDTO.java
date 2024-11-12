package edu.miu.cs489.studyplus.dto.response;

import java.sql.Timestamp;

public record NotificationResponseDTO(
        StudyResponseDTO studyResponseDTO,
        ParticipantResponseDTO participantResponseDTO,
        String subject,
        String message,
        Timestamp sentAt
) {
}
