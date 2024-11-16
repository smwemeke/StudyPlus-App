package edu.miu.cs489.studyplus.dto.response;

import java.time.LocalDate;

public record StudyResponseDTO(
        String studyName,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String studySponsor
) {
}
