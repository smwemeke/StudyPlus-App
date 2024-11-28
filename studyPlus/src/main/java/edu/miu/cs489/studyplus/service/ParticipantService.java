package edu.miu.cs489.studyplus.service;

import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;


import java.util.List;
import java.util.Optional;

public interface ParticipantService {
    Optional<ParticipantResponseDTO> createParticipants(ParticipantRequestDTO participantRequestDTO);
    List<ParticipantResponseDTO> getAllParticipants();
    Optional<ParticipantResponseDTO> findParticipantByUsername(String username);
    void deleteParticipantByUsername(String username);
    Optional<ParticipantResponseDTO> updateParticipant(String username, ParticipantRequestDTO participantRequestDTO);
    Optional<ParticipantResponseDTO> updateParticipantPartially(String username, ParticipantRequestDTO participantRequestDTO);
    ParticipantResponseDTO assignStudies(Long participantId, List<Long> studyId);
}
