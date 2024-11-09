package edu.miu.cs489.studyplus.dto.response;

import edu.miu.cs489.studyplus.model.Address;

import java.time.LocalDate;

public record ParticipantResponseDTO(
        String firstname,
        String lastname,
        String phonenumber,
        String email,
        Address address,
        LocalDate joinDate

){
}
