package edu.miu.cs489.studyplus.repository;

import edu.miu.cs489.studyplus.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByParticipantId(Long participantId);
}
