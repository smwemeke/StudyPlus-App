package edu.miu.cs489.studyplus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record ParticipantRequestDTO(
       @NotNull(message = "blank - null - empty are not accepted")
        String username,
        @NotBlank(message = "blank - null - empty are not accepted")
        String firstname,
        @NotBlank(message = "blank - null - empty are not accepted")
        String lastname,
        @Size(min = 10, max = 12)
        String phonenumber,
        String email,
        @NotNull(message = "blank - null - empty are not accepted")
        LocalDate dob,
        AddressRequestDTO addressRequestDTO,
        @NotNull(message = "blank - null - empty are not accepted")
        LocalDate joinDate,
        List<StudyRequestDTO> studyRequestDTO) {
}
