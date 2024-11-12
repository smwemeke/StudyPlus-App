package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.request.AddressRequestDTO;
import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.AddressResponseDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Address;
import edu.miu.cs489.studyplus.model.Coordinator;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.AddressRepository;
import edu.miu.cs489.studyplus.repository.ParticipantRepository;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import edu.miu.cs489.studyplus.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private  final AddressRepository addressRepository;
    private final ParticipantRepository participantRepository;
    private final StudyRepository studyRepository;

    @Override
    public Optional<ParticipantResponseDTO> addParticipants(ParticipantRequestDTO participantRequestDTO) {

        // Create Address
        Address newAddress = new Address();
        newAddress.setCity(participantRequestDTO.addressRequestDTO().city());
        newAddress.setState(participantRequestDTO.addressRequestDTO().state());
        newAddress.setStreet(participantRequestDTO.addressRequestDTO().street());
        newAddress.setZip(participantRequestDTO.addressRequestDTO().zip());

        Address savedAddress = addressRepository.save(newAddress);

        // Create Study
        Study newStudy = new Study();
        newStudy.setStudyName(participantRequestDTO.studyRequestDTO().studyName());
        newStudy.setDescription(participantRequestDTO.studyRequestDTO().description());
        newStudy.setStartDate(participantRequestDTO.studyRequestDTO().startDate());
        newStudy.setEndDate(participantRequestDTO.studyRequestDTO().endDate());
        newStudy.setStudySponsor(participantRequestDTO.studyRequestDTO().studySponsor());

        Study savedStudy = studyRepository.save(newStudy);

        List<Study> studies = new ArrayList<>();
        studies.add(savedStudy);



        // Create Participant
        Participant newParticipant = new Participant();
        newParticipant.setParticipantId(participantRequestDTO.participantId());
        newParticipant.setFirstname(participantRequestDTO.firstname());
        newParticipant.setLastname(participantRequestDTO.lastname());
        newParticipant.setDob(participantRequestDTO.dob());
        newParticipant.setJoinDate(participantRequestDTO.joinDate());
        newParticipant.setEmail(participantRequestDTO.email());
        newParticipant.setPhonenumber(participantRequestDTO.phonenumber());
        newParticipant.setPrimaryaddress(savedAddress);
        newParticipant.setStudy(studies);

        Participant savedParticipant = participantRepository.save(newParticipant);


        AddressResponseDTO addressResponseDTO = new AddressResponseDTO(
                savedAddress.getStreet(),
                savedAddress.getCity(),
                savedAddress.getState(),
                savedAddress.getZip()
                );

        StudyResponseDTO studyResponseDTO = new StudyResponseDTO(
                savedStudy.getStudyName(),
                savedStudy.getDescription(),
                savedStudy.getStartDate(),
                savedStudy.getEndDate(),
                savedStudy.getStudySponsor()
        );
        ParticipantResponseDTO participantResponseDTO =
               new ParticipantResponseDTO(
                       savedParticipant.getFirstname(),
                       savedParticipant.getLastname(),
                       savedParticipant.getPhonenumber(),
                       savedParticipant.getEmail(),
                       addressResponseDTO,
                       savedParticipant.getJoinDate(),
                       studyResponseDTO
               );
        return Optional.of(participantResponseDTO);
    }

    @Override
    public List<ParticipantRequestDTO> getAllParticipants() {
        return List.of();
    }

    @Override
    public Optional<ParticipantResponseDTO> findByParticipantId(Integer participantId) {
        return Optional.empty();
    }
}
