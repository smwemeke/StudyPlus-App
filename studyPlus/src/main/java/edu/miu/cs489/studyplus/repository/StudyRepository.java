package edu.miu.cs489.studyplus.repository;

import edu.miu.cs489.studyplus.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Optional<Study> findByStudyName(String studyName);
    void deleteStudyByStudyName(String studyName);
}
