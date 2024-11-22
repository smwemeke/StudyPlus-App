package edu.miu.cs489.studyplus.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record StudyRequestDTO(
        //@NotBlank(message = "blank - null - empty are not accepted")
        String studyName,
        String description,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        String studySponsor
        //CoordinatorRequestDTO coordinatorRequestDTO,
        //ParticipantRequestDTO participantRequestDTO,
        //NotificationRequestDTO notificationRequestDTO

) {
}
