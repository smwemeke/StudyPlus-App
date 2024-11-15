package edu.miu.cs489.studyplus.dto.response;

import java.sql.Timestamp;
import java.util.List;

public record NotificationResponseDTO(
        List<StudyResponseDTO> studyResponseDTO,
        ParticipantResponseDTO participantResponseDTO,
        String subject,
        String message,
        Timestamp sentAt
) {
}
