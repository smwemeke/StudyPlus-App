package edu.miu.cs489.studyplus.repository;

import edu.miu.cs489.studyplus.model.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Integer> {
}
