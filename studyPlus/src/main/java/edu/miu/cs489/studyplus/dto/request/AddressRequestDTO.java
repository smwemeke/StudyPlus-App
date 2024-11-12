package edu.miu.cs489.studyplus.dto.request;

public record AddressRequestDTO(
        String street,
        String city,
        String state,
        Integer zip

) {
}
