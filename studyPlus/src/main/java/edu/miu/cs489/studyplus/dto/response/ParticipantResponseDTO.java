package edu.miu.cs489.studyplus.dto.response;

import java.time.LocalDate;

public record ParticipantResponseDTO(
        String firstname,
        String lastname,
        String phonenumber,
        String email,
        AddressResponseDTO addressResponseDTO,
        LocalDate joinDate,
        StudyResponseDTO studyResponseDTO
){
}
