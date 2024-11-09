package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.service.ParticipantService;

import java.util.List;
import java.util.Optional;

public class ParticipantServiceImpl implements ParticipantService {
    @Override
    public Optional<ParticipantResponseDTO> addParticipants(ParticipantRequestDTO participantRequestDTO) {
        return Optional.empty();
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
