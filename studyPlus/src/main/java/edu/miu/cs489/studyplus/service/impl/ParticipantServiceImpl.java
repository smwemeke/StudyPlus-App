package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.mapper.AddressResponseMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyRequestMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyResponseMapper;
import edu.miu.cs489.studyplus.dto.request.NotificationRequestDTO;
import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.AddressResponseDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.exception.UserNotFoundException;
import edu.miu.cs489.studyplus.exception.ValueAlreadyExistsException;
import edu.miu.cs489.studyplus.model.Address;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.ParticipantRepository;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import edu.miu.cs489.studyplus.service.NotificationService;
import edu.miu.cs489.studyplus.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.miu.cs489.studyplus.util.Message.USER_NOT_FOUND;
@Transactional
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final StudyResponseMapper studyResponseMapper;
    private  final StudyRequestMapper studyRequestMapper;
    private final AddressResponseMapper addressResponseMapper;
    private final StudyRepository studyRepository;
    private final NotificationService notificationService;

    @Override
    public Optional<ParticipantResponseDTO> createParticipants(ParticipantRequestDTO participantRequestDTO) {

        // Check if username already exists
        if (participantRepository.existsByUsername(participantRequestDTO.username())) {
            throw new ValueAlreadyExistsException("Username already exists: " + participantRequestDTO.username());
        }

        // Create Participant
        Participant newParticipant = new Participant();
        newParticipant.setUsername(participantRequestDTO.username());
        newParticipant.setFirstName(participantRequestDTO.firstname());
        newParticipant.setLastName(participantRequestDTO.lastname());
        newParticipant.setDob(participantRequestDTO.dob());
        newParticipant.setJoinDate(participantRequestDTO.joinDate());
        newParticipant.setEmail(participantRequestDTO.email());
        newParticipant.setPhoneNumber(participantRequestDTO.phonenumber());
        newParticipant.setAddress(
                new Address(
                        participantRequestDTO.addressRequestDTO().city(),
                        participantRequestDTO.addressRequestDTO().state(),
                        participantRequestDTO.addressRequestDTO().street(),
                        participantRequestDTO.addressRequestDTO().zip()));

        Participant savedParticipant = participantRepository.save(newParticipant);

        // Get the address object first
        Address savedAddress = savedParticipant.getAddress();

        // create List of StudyResponseDTO
        List<StudyResponseDTO> responseDTOList = new ArrayList<>();
        // create participant responseDTO
        ParticipantResponseDTO participantResponseDTO =
                new ParticipantResponseDTO(
                        savedParticipant.getUserId(),
                        savedParticipant.getUsername(),
                        savedParticipant.getFirstName(),
                        savedParticipant.getLastName(),
                        savedParticipant.getPhoneNumber(),
                        savedParticipant.getEmail(),
                        new AddressResponseDTO(
                                savedAddress.getStreet(),
                                savedAddress.getCity(),
                                savedAddress.getState(),
                                savedAddress.getZip()
                        ),
                        savedParticipant.getJoinDate(),
                        responseDTOList
                );
        return Optional.of(participantResponseDTO);

    }
    @Override
    public List<ParticipantResponseDTO> getAllParticipants() {
        // create List of StudyResponseDTO
        List<StudyResponseDTO> responseDTOList = new ArrayList<>();
        List<Participant> participants = participantRepository.findAll();
        return participants.stream()
                .map(participant -> new ParticipantResponseDTO(
                        participant.getUserId(),
                        participant.getUsername(),
                        participant.getFirstName(),
                        participant.getLastName(),
                        participant.getPhoneNumber(),
                        participant.getEmail(),
                        addressResponseMapper.toDTO(participant.getAddress()),
                        participant.getJoinDate(),
                        responseDTOList
                        ))
                .toList();
        }

    @Override
    public Optional<ParticipantResponseDTO> findParticipantByUsername(String username) {

        Optional<Participant> foundParticipant = participantRepository.findParticipantByUsername(username);
        if (foundParticipant.isPresent()) {
            List<StudyResponseDTO> responseDTOList = new ArrayList<>();
            ParticipantResponseDTO participantResponseDTO =
                    new ParticipantResponseDTO(
                            foundParticipant.get().getUserId(),
                            foundParticipant.get().getUsername(),
                            foundParticipant.get().getFirstName(),
                            foundParticipant.get().getLastName(),
                            foundParticipant.get().getPhoneNumber(),
                            foundParticipant.get().getEmail(),
                            foundParticipant.map(participant -> addressResponseMapper.toDTO(participant.getAddress()))
                                    .orElse(null),
                            foundParticipant.get().getJoinDate(),
                           responseDTOList
                    );
            return Optional.of(participantResponseDTO);
        }
        throw new UserNotFoundException(username + " " + USER_NOT_FOUND);
    }
    @Override
    @Transactional
    public void deleteParticipantByUsername(String username) {
        System.out.println("Deleting participant with username: " + username);
        Optional<Participant> foundParticipant =
                participantRepository.findParticipantByUsername(username);
        if (foundParticipant.isPresent()) {
            participantRepository.deleteParticipantByUsername(username);
            System.out.println("Participant deleted.");
        } else {
            throw new UserNotFoundException(username + "not found" );
        }

    }
    @Override
    public Optional<ParticipantResponseDTO> updateParticipant(String username, ParticipantRequestDTO participantRequestDTO) {
        Participant foundParticipant = participantRepository.findParticipantByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(("Participant  with username: " + username + " not found")));
        // Create address
        Address address = new Address();
        address.setState(participantRequestDTO.addressRequestDTO().state());
        address.setZip(participantRequestDTO.addressRequestDTO().zip());
        address.setStreet(participantRequestDTO.addressRequestDTO().street());
        address.setCity(participantRequestDTO.addressRequestDTO().city());

        foundParticipant.setUsername(participantRequestDTO.username());
        foundParticipant.setPhoneNumber(participantRequestDTO.phonenumber());
        foundParticipant.setEmail(participantRequestDTO.email());
        foundParticipant.setLastName(participantRequestDTO.lastname());
        foundParticipant.setFirstName(participantRequestDTO.firstname());
        foundParticipant.setDob(participantRequestDTO.dob());
        foundParticipant.setAddress(address);
        foundParticipant.setJoinDate(participantRequestDTO.joinDate());


            Participant updatedParticipant = participantRepository.save(foundParticipant);
            List<StudyResponseDTO> responseDTOList = new ArrayList<>();

        return Optional.of(new ParticipantResponseDTO(
                updatedParticipant.getUserId(),
                    updatedParticipant.getUsername(),
                    updatedParticipant.getFirstName(),
                    updatedParticipant.getLastName(),
                    updatedParticipant.getPhoneNumber(),
                    updatedParticipant.getEmail(),
                    addressResponseMapper.toDTO(updatedParticipant.getAddress()),
                    updatedParticipant.getJoinDate(),
                    responseDTOList
            ));
        }

    @Override
    @Transactional
    public Optional<ParticipantResponseDTO> updateParticipantPartially(String username, ParticipantRequestDTO participantRequestDTO) {
        Participant foundParticipant = participantRepository.findParticipantByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(("Participant  with username: " + username + " not found")));
        if(participantRequestDTO.firstname() != null){
            foundParticipant.setFirstName(participantRequestDTO.firstname());
        }
        if(participantRequestDTO.lastname() != null){
            foundParticipant.setLastName(participantRequestDTO.lastname());
        }
        if(participantRequestDTO.phonenumber() != null){
                foundParticipant.setPhoneNumber(participantRequestDTO.phonenumber());
            }
        if(participantRequestDTO.email() != null){
            foundParticipant.setEmail(participantRequestDTO.email());
        }
        if(participantRequestDTO.username() != null){
                foundParticipant.setUsername(participantRequestDTO.username());
        }
            Participant updatedParticipant = participantRepository.save(foundParticipant);
            return Optional.of(new ParticipantResponseDTO(
                    updatedParticipant.getUserId(),
                            updatedParticipant.getUsername(),
                            updatedParticipant.getFirstName(),
                            updatedParticipant.getLastName(),
                            updatedParticipant.getPhoneNumber(),
                            updatedParticipant.getEmail(),
                            addressResponseMapper.toDTO(updatedParticipant.getAddress()),
                            updatedParticipant.getJoinDate(),
                            updatedParticipant.getStudy().stream()
                                    .map(studyResponseMapper::toStudyResponseDTO)
                                    .toList()
            ));
        }

    private void notifyParticipantAssigned(Participant participant, Study study)   {
        if(participant==null){
            return;
        }

        String message = String.format("%s you have been enrolled in a new study %s", participant.getFirstName(),study.getStudyName() );
        String subject ="Enrolled in new study successfully";

        notificationService.sendNotification(study, participant, subject, message);
    }

    @Override
    public ParticipantResponseDTO assignStudies(Long participantId, List<Long> studyIds) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException(("Participant not found with ID." + participantId)));

        List<Study> studyList = studyRepository.findAllById(studyIds);
        if(studyList.isEmpty()){
            throw new EntityNotFoundException("No studies found with provided IDs.");
        }
        //Assign studies to the participant
        studyList.forEach(study -> {
            if(!participant.getStudy().contains(study)){
                participant.getStudy().add(study);
                study.getParticipant().add(participant);
            }
        });

       for(Study study : studyList){
           notifyParticipantAssigned(participant, study);
       }
        List<StudyResponseDTO> studyResponseDTOList = studyList.stream()
                .map(study -> new StudyResponseDTO(
                        study.getStudyId(),
                        study.getStudyName(),
                        study.getDescription(),
                        study.getStartDate(),
                        study.getEndDate(),
                        study.getStudySponsor()
                ))
                .toList();

                return  new ParticipantResponseDTO(
                        participant.getUserId(),
                        participant.getUsername(),
                        participant.getFirstName(),
                        participant.getLastName(),
                        participant.getPhoneNumber(),
                        participant.getEmail(),
                        addressResponseMapper.toDTO(participant.getAddress()),
                        participant.getJoinDate(),
                        studyResponseDTOList
        );
    }
}