package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.AddressResponseDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
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

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    @Override
    public Optional<ParticipantResponseDTO> createParticipants(ParticipantRequestDTO participantRequestDTO) {

        System.out.println(participantRequestDTO);
        List<Study> studyList = participantRequestDTO.studyRequestDTO().stream()
                .map(studyRequest -> new Study(
                        studyRequest.studyName(),
                        studyRequest.description(),
                        studyRequest.startDate(),
                        studyRequest.endDate(),
                        studyRequest.studySponsor()
                ))
                .collect(Collectors.toList());

        System.out.println("study list: " + studyList);
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
                        new ArrayList<>(
                                savedStudies.stream()
                                        .map(study -> new StudyResponseDTO(
                                                study.getStudyName(),
                                                study.getDescription(),
                                                study.getStartDate(),
                                                study.getEndDate(),
                                                study.getStudySponsor()
                                        ))
                                        .collect(Collectors.toList()))
                );
        return Optional.of(participantResponseDTO);
    }

    @Override
    public List<ParticipantResponseDTO> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        List<ParticipantResponseDTO> participantResponseDTO = new ArrayList<>();


        for (Participant p : participants) {
            System.out.println("Studies: " + p.getStudy());
            ParticipantResponseDTO participantResponseDTO1 =
                    new ParticipantResponseDTO(
                            p.getFirstname(),
                            p.getLastname(),
                            p.getPhonenumber(),
                            p.getEmail(),
                            new AddressResponseDTO(
                                    p.getAddress().getStreet(),
                                    p.getAddress().getCity(),
                                    p.getAddress().getState(),
                                    p.getAddress().getZip()
                            ),
                            p.getJoinDate(),
                            p.getStudy().stream()
                                    .map(study -> new StudyResponseDTO(
                                            study.getStudyName(),
                                            study.getDescription(),
                                            study.getStartDate(),
                                            study.getEndDate(),
                                            study.getStudySponsor()
                                    ))
                                    .toList()
                    );
            participantResponseDTO.add(participantResponseDTO1);
        }

        return participantResponseDTO;
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
                            new AddressResponseDTO(foundParticipant.get().getAddress().getStreet(),
                                    foundParticipant.get().getAddress().getCity(),
                                    foundParticipant.get().getAddress().getState(),
                                    foundParticipant.get().getAddress().getZip()
                            ),
                            foundParticipant.get().getJoinDate(),
                            foundParticipant.get().getStudy().stream()
                                    .map(study -> new StudyResponseDTO(
                                            study.getStudyName(),
                                            study.getDescription(),
                                            study.getStartDate(),
                                            study.getEndDate(),
                                            study.getStudySponsor()
                                    ))
                                    .toList()
                    );
            return Optional.of(participantResponseDTO);
        }
        throw new UserNotFoundException(username + " not found");
    }

    @Override
    @Transactional
    public void deleteParticipantByUsername(String username) {
        Optional<Participant> foundParticipant =
                participantRepository.findParticipantByUsername(username);
        if (foundParticipant.isPresent()) {
            participantRepository.deleteParticipantByUsername(username);
        } else {
            throw new UserNotFoundException(username + " not found");
        }
    }
}

