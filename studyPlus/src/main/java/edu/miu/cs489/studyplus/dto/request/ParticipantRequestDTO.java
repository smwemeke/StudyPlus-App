package edu.miu.cs489.studyplus.dto.request;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record ParticipantRequestDTO(
       //@NotNull(message = "blank - null - empty are not accepted")
        String username,
        String firstname,
        String lastname,
        @Size(min = 10, max = 12)
        String phonenumber,
        String email,
        LocalDate dob,
        AddressRequestDTO addressRequestDTO,
        LocalDate joinDate)
{
}
