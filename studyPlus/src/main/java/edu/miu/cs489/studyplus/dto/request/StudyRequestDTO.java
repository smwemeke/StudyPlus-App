package edu.miu.cs489.studyplus.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record StudyRequestDTO(
        @NotBlank(message = "blank - null - empty are not accepted")
        String studyName,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String studySponsor
        //CoordinatorRequestDTO coordinatorRequestDTO,
        //ParticipantRequestDTO participantRequestDTO,
        //NotificationRequestDTO notificationRequestDTO

) {
}
