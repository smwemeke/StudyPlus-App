package edu.miu.cs489.studyplus.dto.response;

public record AddressResponseDTO(
        String street,
        String city,
        String state,
        Integer zip
) {
}
