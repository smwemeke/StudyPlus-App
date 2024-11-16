package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.mapper.StudyRequestMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyResponseMapper;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import edu.miu.cs489.studyplus.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final StudyResponseMapper studyResponseMapper;
    private final StudyRequestMapper studyRequestMapper;

    @Override
    public Optional<StudyResponseDTO> createStudy(StudyRequestDTO studyRequestDTO) {
        // Create Study
        Study newStudy = studyRequestMapper.toStudy(studyRequestDTO);
        // Save createdStudy
        Study savedStudy = studyRepository.save(newStudy);
        // Create Study Response
        StudyResponseDTO studyResponseDTO = studyResponseMapper.toStudyResponseDTO(savedStudy);
        return Optional.of(studyResponseDTO);
    }
}
