package edu.miu.cs489.studyplus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record StudyRequestDTO(
        @NotNull(message = "Study must not be empty")
        String studyName,
        @NotNull(message = "Description must not be empty")
        String description,
        @NotNull(message = "Start Date must not be null")
        @DateTimeFormat(pattern = "MM-dd-yyyy")
        LocalDate startDate,
        @NotNull(message = "End Date must not be null")
        @DateTimeFormat(pattern = "MM-dd-yyyy")
        LocalDate endDate,
        @NotNull
        String studySponsor
        //CoordinatorRequestDTO coordinatorRequestDTO,
        //ParticipantRequestDTO participantRequestDTO,
        //NotificationRequestDTO notificationRequestDTO

) {
}
