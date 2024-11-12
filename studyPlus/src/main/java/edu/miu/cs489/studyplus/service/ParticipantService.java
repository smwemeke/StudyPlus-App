package edu.miu.cs489.studyplus.service;

import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public interface ParticipantService {
    Optional<ParticipantResponseDTO> addParticipants(ParticipantRequestDTO participantRequestDTO);
    List<ParticipantRequestDTO> getAllParticipants();
    Optional<ParticipantResponseDTO> findByParticipantId(Integer participantId);

}
