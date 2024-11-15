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


//    Optional<ParticipantResponseDTO> updateParticipant(Long participantId, ParticipantRequestDTO participantRequestDTO);
//    Optional<ParticipantResponseDTO> updateParticipantPartially(Long participantId, ParticipantRequestDTO participantRequestDTO);

}
