package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.mapper.StudyRequestMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyResponseMapper;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.exception.UserNotFoundException;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import edu.miu.cs489.studyplus.service.StudyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static edu.miu.cs489.studyplus.util.Message.USER_NOT_FOUND;

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

    @Override
    public List<StudyResponseDTO> getAllStudies() {
        List<Study> studies = studyRepository.findAll();
        return studies.stream()
                .map(study -> new StudyResponseDTO(
                        study.getStudyName(),
                        study.getDescription(),
                        study.getStartDate(),
                        study.getEndDate(),
                        study.getStudySponsor()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudyResponseDTO> findStudyByName(String studyName) {
        Optional<Study> foundStudy = studyRepository.findStudyByStudyName(studyName);
        if (foundStudy.isPresent()) {
            StudyResponseDTO studyResponseDTO =
                    new StudyResponseDTO(
                            foundStudy.get().getStudyName(),
                            foundStudy.get().getDescription(),
                            foundStudy.get().getStartDate(),
                            foundStudy.get().getEndDate(),
                            foundStudy.get().getStudySponsor());
            return Optional.of(studyResponseDTO);
        }
        throw new UserNotFoundException(studyName + " " + USER_NOT_FOUND);
    }

    @Override
    @Transactional
    public void deleteStudyByStudyName(String studyName) {
        Optional<Study> foundStudy = studyRepository.findStudyByStudyName(studyName);
        if (foundStudy.isPresent()) {
            studyRepository.deleteStudyByStudyName(studyName);
        } else {
            throw new UserNotFoundException(studyName + " not found ");
        }
    }
    @Override
    public Optional<StudyResponseDTO> updateStudy(String studyName, StudyRequestDTO studyRequestDTO) {
        Optional<Study> study = studyRepository.findStudyByStudyName(studyName);
        if (study.isPresent()) {
            Study savedStudy = study.get();
            savedStudy.setStudyName(studyRequestDTO.studyName());
            savedStudy.setDescription(studyRequestDTO.description());
            savedStudy.setStartDate(studyRequestDTO.startDate());
            savedStudy.setEndDate(studyRequestDTO.endDate());
            savedStudy.setStudySponsor(studyRequestDTO.studySponsor());

            Study updatedStudy = studyRepository.save(savedStudy);

            return Optional.of(new StudyResponseDTO(
                    updatedStudy.getStudyName(),
                    updatedStudy.getDescription(),
                    updatedStudy.getStartDate(),
                    updatedStudy.getEndDate(),
                    updatedStudy.getStudySponsor()
            ));
        }
        throw new UserNotFoundException(studyName + " " + USER_NOT_FOUND);
    }

    @Override
    public Optional<StudyResponseDTO> updateStudyPartially(String studyName, StudyRequestDTO studyRequestDTO) {
        Optional<Study> study = studyRepository.findStudyByStudyName(studyName);
        if (study.isPresent()) {
            Study savedStudy = study.get();
            if (studyRequestDTO.endDate() != null) {
                savedStudy.setEndDate(studyRequestDTO.endDate());
            }
            Study updatedStudy = studyRepository.save(savedStudy);
            System.out.println(updatedStudy);
            return Optional.of(
                    new StudyResponseDTO(
                            updatedStudy.getStudyName(),
                            updatedStudy.getDescription(),
                            updatedStudy.getStartDate(),
                            updatedStudy.getEndDate(),
                            updatedStudy.getStudySponsor()
                    )
            );
        } else {
            throw new UserNotFoundException(studyName + " " + USER_NOT_FOUND);
        }
    }
}
