package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.mapper.StudyRequestMapper;
import edu.miu.cs489.studyplus.dto.mapper.StudyResponseMapper;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceImplTest {
    @Mock
    private StudyRepository studyRepository;

    @Mock
    private StudyRequestMapper studyRequestMapper;

    @Mock
    private StudyResponseMapper studyResponseMapper;

    @InjectMocks
    private  StudyServiceImpl studyService;

    @Test
    void createStudy() {
        //Define mock behaviour
        StudyRequestDTO studyRequestDTO =
                new StudyRequestDTO(
                        "SickleCell",
                        "Treat disease",
                        LocalDate.of(2021,11,23),
                        LocalDate.of(2026,12,20),
                        "NIH");
        Study mappedStudy =
                new Study(
                        studyRequestDTO.studyName(),
                        studyRequestDTO.description(),
                        studyRequestDTO.startDate(),
                        studyRequestDTO.endDate(),
                        studyRequestDTO.studySponsor());

        Study savedStudy = mappedStudy;

        StudyResponseDTO expectedResponse =
                new StudyResponseDTO(
                        "SickleCell",
                        "Treat disease",
                        LocalDate.of(2021, 11, 23),
                        LocalDate.of(2026, 12, 20),
                        "NIH");

        // define what mock should do when method is call
        Mockito.when(studyRequestMapper.toStudy(studyRequestDTO)).thenReturn(mappedStudy);
        Mockito.when(studyRepository.save(Mockito.any(Study.class))).thenReturn(savedStudy);
        Mockito.when(studyResponseMapper.toStudyResponseDTO(savedStudy)).thenReturn(expectedResponse);

        // call method we are testing
        Optional<StudyResponseDTO> studyResponseDTO = studyService.createStudy(studyRequestDTO);

        // Assert the result
        assertTrue(studyResponseDTO.isPresent());
        assertEquals("SickleCell", studyResponseDTO.get().studyName());
        assertEquals("Treat disease", studyResponseDTO.get().description());
        assertEquals(LocalDate.of(2021, 11, 23), studyResponseDTO.get().startDate());
        assertEquals(LocalDate.of(2026, 12, 20), studyResponseDTO.get().endDate());
        assertEquals("NIH", studyResponseDTO.get().studySponsor());

        // Verify that the save method was called once
        Mockito.verify(studyRepository, Mockito.times(1)).save(Mockito.any(Study.class));
    }

    @Test
    void getAllStudies() {
        List<Study> studyList =
                List.of(
                        new Study( "SickleCell","Treat disease",LocalDate.of(2021, 11, 23),
                                LocalDate.of(2026, 12, 20),"NIH"),
                        new Study("DiabetesResearch","Exploring new treatments for diabetes",LocalDate.of(2022, 1, 15),
                                LocalDate.of(2027, 3, 30),"World Health Organization"),
                        new Study("CancerImmunotherapy","Developing advanced immunotherapy treatments",LocalDate.of(2020, 5, 10),
                                LocalDate.of(2025, 8, 25),"National Cancer Institute")
                );

        List<StudyResponseDTO> expectedResponseDTO =
        List.of(
                new StudyResponseDTO( "SickleCell","Treat disease",LocalDate.of(2021, 11, 23),
                        LocalDate.of(2026, 12, 20),"NIH"),
                new StudyResponseDTO("DiabetesResearch","Exploring new treatments for diabetes",LocalDate.of(2022, 1, 15),
                        LocalDate.of(2027, 3, 30),"World Health Organization"),
                new StudyResponseDTO("CancerImmunotherapy","Developing advanced immunotherapy treatments",LocalDate.of(2020, 5, 10),
                LocalDate.of(2025, 8, 25),"National Cancer Institute")
        );

        // Mock service repository
        Mockito.when(studyRepository.findAll()).thenReturn(studyList);

        // calling service method
        List<StudyResponseDTO> actualResponseDTO = studyService.getAllStudies();

        Assertions.assertEquals(expectedResponseDTO, actualResponseDTO);
    }
    @Test
    void findStudyByName() {
        String studyName = "COVID";
        Study exisitingStudy = new Study(
                "COVID",
                "COVID Research",
                LocalDate.of(2021,12,12),
                LocalDate.of(2023,12,12),
                "MOH");

        Mockito.when(studyRepository.findStudyByStudyName(studyName)).thenReturn(Optional.of(exisitingStudy));
        studyService.findStudyByName(studyName);

        Mockito.verify(studyRepository,Mockito.times(1)).findStudyByStudyName(studyName);
    }
    @Test
    void deleteStudyByStudyName() {
        String studyName = "COVID";
        Study exisitingStudy = new Study(
                "COVID",
                "COVID Research",
                LocalDate.of(2021,12,12),
                LocalDate.of(2023,12,12),
                "MOH");

        Mockito.when(studyRepository.findStudyByStudyName(studyName)).thenReturn(Optional.of(exisitingStudy));
        studyService.deleteStudyByStudyName(studyName);

        Mockito.verify(studyRepository,Mockito.times(1)).deleteStudyByStudyName(studyName);
    }

    @Test
    void updateStudy() {
        String studyName = "SickleCell";
        StudyRequestDTO studyRequestDTO =
                new StudyRequestDTO(
                        "SickleCell",
                        "Treat disease",
                        LocalDate.of(2021,11,23),
                        LocalDate.of(2026,12,20),
                        "NIH");

        Study exisitingStudy = new Study("COVID","COVID Research",LocalDate.of(2021,12,12),
                LocalDate.of(2023,12,12),"MOH");

        Study updatedStudy = new Study("SickleCell", "Treat disease",LocalDate.of(2021,11,23),
                        LocalDate.of(2026,12,20),"NIH");

        Mockito.when(studyRepository.findStudyByStudyName(studyName)).thenReturn(Optional.of(exisitingStudy));

        Mockito.when(studyRepository.save(Mockito.any(Study.class))).thenReturn(updatedStudy);

        Optional<StudyResponseDTO>studyResponseDTO = studyService.updateStudy(studyName,studyRequestDTO);
        assertEquals("SickleCell",studyResponseDTO.get().studyName());
        assertEquals("Treat disease",studyResponseDTO.get().description());
        assertEquals(LocalDate.of(2021,11,23),studyResponseDTO.get().startDate());
        assertEquals(LocalDate.of(2026,12,20),studyResponseDTO.get().endDate());
        assertEquals("NIH",studyResponseDTO.get().studySponsor());
    }
}