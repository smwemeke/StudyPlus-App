package edu.miu.cs489.studyplus.controller;

import edu.miu.cs489.studyplus.dto.request.ParticipantRequestDTO;
import edu.miu.cs489.studyplus.dto.response.ParticipantResponseDTO;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.repository.ParticipantRepository;
import edu.miu.cs489.studyplus.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/p1/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping
    public ResponseEntity<ParticipantResponseDTO> createParticipants(
            @RequestBody
            @Valid
            ParticipantRequestDTO participantRequestDTO
    ){
        Optional<ParticipantResponseDTO> participantResponseDTO = participantService.createParticipants(participantRequestDTO);
        if(participantResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(participantResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponseDTO>> getAllParticipants(){
        List<ParticipantResponseDTO> allParticipants = participantService.getAllParticipants();
        return ResponseEntity.status(HttpStatus.OK).body(allParticipants);
    }

    @GetMapping("/{username}")
    public ResponseEntity<ParticipantResponseDTO> getParticipantByUsername(@PathVariable String username){
        Optional<ParticipantResponseDTO> participantResponseDTO =
                participantService.findParticipantByUsername(username);
        if(participantResponseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(participantResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{username}")
    public void deleteParticipantByUsername(@PathVariable String username){
        participantService.deleteParticipantByUsername(username);
    }

    @PutMapping
    public ResponseEntity<ParticipantResponseDTO> updateParticipant(
            @RequestParam
            String username,
            @RequestBody @Valid ParticipantRequestDTO participantRequestDTO){
        Optional<ParticipantResponseDTO> participantResponseDTO = participantService.updateParticipant(username, participantRequestDTO);
        if(participantResponseDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(participantResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


@PatchMapping("/{username}")
public ResponseEntity<ParticipantResponseDTO> updateParticipants(
        @PathVariable String username,
        @RequestBody @Valid ParticipantRequestDTO participantRequestDTO){
    Optional<ParticipantResponseDTO> participantResponseDTO = participantService.updateParticipantPartially(username, participantRequestDTO);
    if(participantResponseDTO.isPresent()){
        return ResponseEntity.status(HttpStatus.OK).body(participantResponseDTO.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
}


}