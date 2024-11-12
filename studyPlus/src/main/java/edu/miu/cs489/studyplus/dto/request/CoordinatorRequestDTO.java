package edu.miu.cs489.studyplus.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CoordinatorRequestDTO(
         Integer coordinatorId,
         @NotBlank(message = "blank - null - empty are not accepted")
         String firstname,
         @NotBlank(message = "blank - null - empty are not accepted")
         String lastname,
         @Size(min = 10, max = 12)
         String phonenumber,
         String email,
         StudyRequestDTO studyRequestDTO
) {
}
