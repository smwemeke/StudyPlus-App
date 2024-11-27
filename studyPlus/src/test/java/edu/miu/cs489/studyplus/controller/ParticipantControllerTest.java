package edu.miu.cs489.studyplus.controller;

import edu.miu.cs489.studyplus.dto.request.AddressRequestDTO;
import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.AddressResponseDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import edu.miu.cs489.studyplus.service.ParticipantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @Test
    void createParticipants() {
//        List<StudyRequestDTO> studyRequestDTOS = new ArrayList<>();
//        studyRequestDTOS.add(new StudyRequestDTO(
//                "Study2",
//                "study2 description",
//                LocalDate.of(2020,07,25),
//                LocalDate.of(2023,07,26),
//                "WHO"
//        ));
//        studyRequestDTOS.add(new StudyRequestDTO(
//                "Study1",
//                "study1 description",
//                LocalDate.of(2020,12,25),
//                LocalDate.of(2023,11,26),
//                "NIH"
//        ));
        ParticipantRequestDTO participantRequestDTO =
                new ParticipantRequestDTO(
                       "smwemeke",
                        "Sue",
                        "mwemeke",
                        "773816767",
                       "smwemeke@gmail.com",
                        LocalDate.of(2023,12,23),
                        new AddressRequestDTO(
                                "145E",
                                "SLC",
                                "Utah",
                                8675
                        ),
                        LocalDate.of(2021,03,30)
                );

        List<StudyResponseDTO> studyResponseDTOS = new ArrayList<>();
        studyResponseDTOS.add(new StudyResponseDTO(
                        "Study1",
                        "study1 description",
                        LocalDate.of(2020,12,25),
                        LocalDate.of(2023,11,26),
                        "NIH"
                ));
        studyResponseDTOS.add(
                new StudyResponseDTO(
                        "Study2",
                        "study2 description",
                        LocalDate.of(2020,07,25),
                        LocalDate.of(2023,07,26),
                        "WHO"
                ));

        ParticipantResponseDTO  responseDTO =new ParticipantResponseDTO(
                "Sue",
                "mwemeke",
                "773816767",
                "smwemeke@gmail.com",
                new AddressResponseDTO(
                        "145E",
                        "SLC",
                        "Utah",
                        8675
                ),
                LocalDate.of(2021,03,30),
                studyResponseDTOS
        );

        Mockito.when(participantService.createParticipants(participantRequestDTO)).thenReturn(Optional.of(responseDTO));

        ResponseEntity<?> responseEntity = participantController.createParticipants(participantRequestDTO);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(responseDTO, responseEntity.getBody());
    }
    @Test
    void getAllParticipants() {
        List<StudyResponseDTO> studyResponseDTOS = new ArrayList<>();
        studyResponseDTOS.add(new StudyResponseDTO(
                "Study1",
                "study1 description",
                LocalDate.of(2020,12,25),
                LocalDate.of(2023,11,26),
                "NIH"
        ));
        studyResponseDTOS.add(
                new StudyResponseDTO(
                        "Study2",
                        "study2 description",
                        LocalDate.of(2020,07,25),
                        LocalDate.of(2023,07,26),
                        "WHO"
                ));

        List<ParticipantResponseDTO> participantResponseDTOS=
                List.of(
                        new ParticipantResponseDTO(
                                "Sue",
                                "mwemeke",
                                "773816767",
                                "smwemeke@gmail.com",
                                new AddressResponseDTO(
                                        "145E",
                                        "SLC",
                                        "Utah",
                                        8675
                                ),
                                LocalDate.of(2021,03,30),
                                studyResponseDTOS
                        ),
                        new ParticipantResponseDTO(
                                "grace",
                                "mwemeke",
                                "789654",
                                "gmwemeke@gmail.com",
                                new AddressResponseDTO(
                                        "908 N",
                                        "Fairfield",
                                        "Iowa",
                                        52257
                                ),
                                LocalDate.of(2021,03,30),
                                studyResponseDTOS
                        )
                );

        Mockito.when(participantService.getAllParticipants())
                .thenReturn(participantResponseDTOS);
           ResponseEntity<List<ParticipantResponseDTO>> listResponseEntity =
                   participantController.getAllParticipants();

           Assertions.assertEquals(HttpStatus.OK,
                   listResponseEntity.getStatusCode());

           Assertions.assertEquals(participantResponseDTOS,
                   listResponseEntity.getBody());
    }

    @Test
    void getParticipantByUsername() {
    }

//    @Test
//    void deleteParticipantByUsername() {
//    }

//    @Test
//    void updateParticipant() {
//    }
}