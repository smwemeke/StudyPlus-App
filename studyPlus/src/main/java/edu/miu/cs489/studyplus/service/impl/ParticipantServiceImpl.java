package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.mapper.AddressResponseMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyRequestMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyResponseMapper;
import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.AddressResponseDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.exception.UserNotFoundException;
import edu.miu.cs489.studyplus.model.Address;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.ParticipantRepository;
import edu.miu.cs489.studyplus.service.ParticipantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static edu.miu.cs489.studyplus.util.Message.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final StudyResponseMapper studyResponseMapper;
    private  final StudyRequestMapper studyRequestMapper;
    private final AddressResponseMapper addressResponseMapper;

    @Override
    public Optional<ParticipantResponseDTO> createParticipants(ParticipantRequestDTO participantRequestDTO) {

        List<Study> studyList = participantRequestDTO.studyRequestDTO().stream()
                .map(studyRequestMapper::toStudy)
                .collect(Collectors.toList());

        List<StudyRequestDTO> studies = participantRequestDTO.studyRequestDTO();
        for (StudyRequestDTO requestDTO : studies) {
            requestDTO.studyName();
            requestDTO.description();
            requestDTO.startDate();
            requestDTO.endDate();
            requestDTO.studySponsor();
        }

        // Create Participant
        Participant newParticipant = new Participant();
        newParticipant.setUsername(participantRequestDTO.username());
        newParticipant.setFirstname(participantRequestDTO.firstname());
        newParticipant.setLastname(participantRequestDTO.lastname());
        newParticipant.setDob(participantRequestDTO.dob());
        newParticipant.setJoinDate(participantRequestDTO.joinDate());
        newParticipant.setEmail(participantRequestDTO.email());
        newParticipant.setPhonenumber(participantRequestDTO.phonenumber());
        newParticipant.setAddress(
                new Address(
                        participantRequestDTO.addressRequestDTO().city(),
                        participantRequestDTO.addressRequestDTO().state(),
                        participantRequestDTO.addressRequestDTO().street(),
                        participantRequestDTO.addressRequestDTO().zip()));
        newParticipant.setStudy(studyList);

        Participant savedParticipant = participantRepository.save(newParticipant);

        // Get the address object first
        Address savedAddress = savedParticipant.getAddress();
        // Get the study object first
        List<Study> savedStudies = savedParticipant.getStudy();

        // create participant responseDTO
        ParticipantResponseDTO participantResponseDTO =
                new ParticipantResponseDTO(
                        savedParticipant.getFirstname(),
                        savedParticipant.getLastname(),
                        savedParticipant.getPhonenumber(),
                        savedParticipant.getEmail(),
                        new AddressResponseDTO(
                                savedAddress.getStreet(),
                                savedAddress.getCity(),
                                savedAddress.getState(),
                                savedAddress.getZip()
                        ),
                        savedParticipant.getJoinDate(),
                                savedStudies.stream()
                                        .map(studyResponseMapper::toStudyResponseDTO)
                                        .collect(Collectors.toList())
                );
        return Optional.of(participantResponseDTO);
    }
    @Override
    public List<ParticipantResponseDTO> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream()
                .map(participant -> new ParticipantResponseDTO(
                        participant.getFirstname(),
                        participant.getLastname(),
                        participant.getPhonenumber(),
                        participant.getEmail(),
                        addressResponseMapper.toDTO(participant.getAddress()),
                        participant.getJoinDate(),
                        participant.getStudy().stream()
                                .map(studyResponseMapper::toStudyResponseDTO)
                                .collect(Collectors.toList())

                        ))
                .collect(Collectors.toList());
        }

    @Override
    public Optional<ParticipantResponseDTO> findParticipantByUsername(String username) {
        Optional<Participant> foundParticipant = participantRepository.findParticipantByUsername(username);
        if (foundParticipant.isPresent()) {
            ParticipantResponseDTO participantResponseDTO =
                    new ParticipantResponseDTO(
                            foundParticipant.get().getFirstname(),
                            foundParticipant.get().getLastname(),
                            foundParticipant.get().getPhonenumber(),
                            foundParticipant.get().getEmail(),
                            foundParticipant.map(participant -> addressResponseMapper.toDTO(participant.getAddress()))
                                    .orElse(null),
                            foundParticipant.get().getJoinDate(),
                            foundParticipant.get().getStudy().stream()
                                    .map(studyResponseMapper::toStudyResponseDTO)
                                    .toList()
                    );
            return Optional.of(participantResponseDTO);
        }
        throw new UserNotFoundException(username + " " + USER_NOT_FOUND);
    }
    @Override
    @Transactional
    public void deleteParticipantByUsername(String username) {
        Optional<Participant> foundParticipant =
                participantRepository.findParticipantByUsername(username);
        if (foundParticipant.isPresent()) {
            participantRepository.deleteParticipantByUsername(username);
        } else {
            throw new UserNotFoundException(username + " " + USER_NOT_FOUND);
        }
    }
    @Override
    public Optional<ParticipantResponseDTO> updateParticipant(String username, ParticipantRequestDTO participantRequestDTO) {

        // Create address
        Address address = new Address();
        address.setState(participantRequestDTO.addressRequestDTO().state());
        address.setZip(participantRequestDTO.addressRequestDTO().zip());
        address.setStreet(participantRequestDTO.addressRequestDTO().street());
        address.setCity(participantRequestDTO.addressRequestDTO().city());

        List<StudyRequestDTO> studyRequestDTOList = participantRequestDTO.studyRequestDTO();
        List<Study> studies  = new ArrayList<>();

        for(StudyRequestDTO requestDTO :studyRequestDTOList){
            Study study = new Study();
            study.setStudyName(requestDTO.studyName());
            study.setDescription(requestDTO.description());
            study.setStudySponsor(requestDTO.studySponsor());
            study.setStartDate(requestDTO.startDate());
            study.setEndDate(requestDTO.endDate());

            studies.add(study);
        }

        Optional<Participant> foundParticipant = participantRepository.findParticipantByUsername(username);
        if (foundParticipant.isPresent()) {
            Participant participant = foundParticipant.get();
            participant.setUsername(participantRequestDTO.username());
            participant.setPhonenumber(participantRequestDTO.phonenumber());
            participant.setEmail(participantRequestDTO.email());
            participant.setLastname(participantRequestDTO.lastname());
            participant.setFirstname(participantRequestDTO.firstname());
            participant.setDob(participantRequestDTO.dob());
            participant.setAddress(address);
            participant.setJoinDate(participantRequestDTO.joinDate());
            participant.setStudy(studies);

            Participant updatedParticipant = participantRepository.save(participant);
            return Optional.of(new ParticipantResponseDTO(
                    updatedParticipant.getFirstname(),
                    updatedParticipant.getLastname(),
                    updatedParticipant.getPhonenumber(),
                    updatedParticipant.getEmail(),
                    addressResponseMapper.toDTO(updatedParticipant.getAddress()),
                    updatedParticipant.getJoinDate(),
                    updatedParticipant.getStudy().stream()
                                    .map(studyResponseMapper::toStudyResponseDTO)
                                    .collect(Collectors.toList())
            ));
        }
        throw new UserNotFoundException(username + " " + USER_NOT_FOUND);
    }

    @Override
    @Transactional
    public Optional<ParticipantResponseDTO> updateParticipantPartially(String username, ParticipantRequestDTO participantRequestDTO) {
        Optional<Participant> foundParticipant = participantRepository.findParticipantByUsername(username);
        if(foundParticipant.isPresent()){
            Participant participant = foundParticipant.get();
            if(participantRequestDTO.phonenumber() != null){
                participant.setPhonenumber(participantRequestDTO.phonenumber());
            }
            if(participantRequestDTO.email() != null){
                participant.setEmail(participantRequestDTO.email());
            }
            Participant updatedParticipant = participantRepository.save(participant);
            return Optional.of(new ParticipantResponseDTO(
                            updatedParticipant.getFirstname(),
                            updatedParticipant.getLastname(),
                            updatedParticipant.getPhonenumber(),
                            updatedParticipant.getEmail(),
                            addressResponseMapper.toDTO(updatedParticipant.getAddress()),
                            updatedParticipant.getJoinDate(),
                            updatedParticipant.getStudy().stream()
                                    .map(studyResponseMapper::toStudyResponseDTO)
                                    .collect(Collectors.toList())
            ));
        }else {
            throw new UserNotFoundException(username + " not found");
        }
    }
}