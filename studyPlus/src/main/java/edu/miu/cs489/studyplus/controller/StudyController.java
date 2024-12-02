package edu.miu.cs489.studyplus.controller;

import edu.miu.cs489.studyplus.dto.request.StudyRequestDTO;
import edu.miu.cs489.studyplus.dto.response.StudyResponseDTO;
import edu.miu.cs489.studyplus.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<StudyResponseDTO>> getAllStudies(){
        List<StudyResponseDTO>allStudies = studyService.getAllStudies();
        return ResponseEntity.status(HttpStatus.OK).body(allStudies);
    }
    @DeleteMapping("/{studyName}")
    public ResponseEntity<Void> deleteStudy(@PathVariable String studyName) {
        Optional<StudyResponseDTO> studyResponseDTO = studyService.findStudyByName(studyName);
        if (studyResponseDTO.isPresent()) {
            studyService.deleteStudyByStudyName(studyName);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    @GetMapping("/{studyName}")
    public ResponseEntity<StudyResponseDTO> findStudyByStudyName(@PathVariable String studyName){
        Optional<StudyResponseDTO> studyResponseDTO =  studyService.findStudyByName(studyName);
        if(studyResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(studyResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PutMapping("/{studyName}")
    public ResponseEntity<StudyResponseDTO> updateStudy(
            @PathVariable String studyName,
            @RequestBody @Valid StudyRequestDTO studyRequestDTO
    ){
       Optional<StudyResponseDTO> studyResponseDTO1 = studyService.updateStudy(studyName, studyRequestDTO);
        if (studyResponseDTO1.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(studyResponseDTO1.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    @PatchMapping
    public ResponseEntity<StudyResponseDTO> updateStudyPartially(
            @RequestParam String studyName,
            @RequestBody StudyRequestDTO studyRequestDTO
    ){
        Optional<StudyResponseDTO> studyResponseDTO = studyService.updateStudy(studyName, studyRequestDTO);
        if(studyResponseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(studyResponseDTO.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}