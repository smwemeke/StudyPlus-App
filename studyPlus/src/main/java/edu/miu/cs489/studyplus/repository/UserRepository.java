package edu.miu.cs489.studyplus.repository;

import edu.miu.cs489.studyplus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}