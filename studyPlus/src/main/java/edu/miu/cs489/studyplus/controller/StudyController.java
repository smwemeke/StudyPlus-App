package edu.miu.cs489.studyplus.controller;

import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/studies")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @PostMapping
    public ResponseEntity<StudyResponseDTO> createStudies(
            @RequestBody
            @Valid
            StudyRequestDTO studyRequestDTO){
        Optional<StudyResponseDTO> studyResponseDTO =
                studyService.createStudy(studyRequestDTO);
        if(studyResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(studyResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
