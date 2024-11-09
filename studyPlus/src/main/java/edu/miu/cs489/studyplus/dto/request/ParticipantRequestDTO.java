package edu.miu.cs489.studyplus.dto.request;

import edu.miu.cs489.studyplus.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ParticipantRequestDTO(
        @NotBlank(message = "blank - null - empty are not accepted")
        String firstname,
        @NotBlank(message = "blank - null - empty are not accepted")
        String lastname,
        @Size(min = 10, max = 12)
        String phonenumber,

        String email,
        @NotBlank(message = "blank - null - empty are not accepted")
        LocalDate dob,
        @NotBlank(message = "blank - null - empty are not accepted")
        Address address,
        LocalDate joinDate) {
}
