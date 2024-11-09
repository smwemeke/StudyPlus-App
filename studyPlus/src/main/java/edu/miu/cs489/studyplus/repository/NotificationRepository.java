package edu.miu.cs489.studyplus.repository;

import edu.miu.cs489.studyplus.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
