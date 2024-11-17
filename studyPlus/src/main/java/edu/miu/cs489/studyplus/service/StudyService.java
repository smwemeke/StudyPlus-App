package edu.miu.cs489.studyplus.service;


import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;

import java.util.List;
import java.util.Optional;

public interface StudyService {

    Optional<StudyResponseDTO> createStudy(StudyRequestDTO studyRequestDTO);
    List<StudyResponseDTO> getAllStudies();
     Optional<StudyResponseDTO> findStudyByName(String studyName);
    void deleteStudyByStudyId(Integer studyId);
//    Optional<StudyResponseDTO> updateStudy(String studyName, StudyRequestDTO studyRequestDTO);
//    Optional<StudyResponseDTO> updateStudyPartially(String studyName, StudyRequestDTO studyRequestDTO);

}
