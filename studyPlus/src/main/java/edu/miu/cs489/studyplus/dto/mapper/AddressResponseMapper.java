package edu.miu.cs489.studyplus.dto.mapper;

import edu.miu.cs489.studyplus.dto.response.AddressResponseDTO;
import edu.miu.cs489.studyplus.model.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressResponseMapper {
    public AddressResponseDTO toDTO(Address address){
        if(address == null){
            return null;
        }
        return new AddressResponseDTO(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip()
        );
    }
}
