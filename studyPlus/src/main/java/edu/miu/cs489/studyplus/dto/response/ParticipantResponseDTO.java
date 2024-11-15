package edu.miu.cs489.studyplus.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ParticipantResponseDTO(
        String firstname,
        String lastname,
        String phonenumber,
        String email,
        AddressResponseDTO addressResponseDTO,
        LocalDate joinDate,
        List<StudyResponseDTO> studyResponseDTO


){
}
