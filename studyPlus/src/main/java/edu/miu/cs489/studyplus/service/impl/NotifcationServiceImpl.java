package edu.miu.cs489.studyplus.service.impl;

import edu.miu.cs489.studyplus.dto.request.NotificationRequestDTO;
import edu.miu.cs489.studyplus.exception.UserNotFoundException;
import edu.miu.cs489.studyplus.model.Notification;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.model.Study;
import edu.miu.cs489.studyplus.repository.NotificationRepository;
import edu.miu.cs489.studyplus.repository.ParticipantRepository;
import edu.miu.cs489.studyplus.repository.StudyRepository;
import edu.miu.cs489.studyplus.service.EmailService;
import edu.miu.cs489.studyplus.service.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotifcationServiceImpl implements NotificationService {
    private final ParticipantRepository participantRepository;
    private final StudyRepository studyRepository;
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Study study, Participant participant, String subject, String message) {



        // Create notification
        Notification notification = new Notification();
        notification.setStudy(study);
        notification.setParticipant(participant);
        notification.setSubject(subject);
        notification.setMessage(message);
        notification.setSentAt(LocalDateTime.now());

        // save notifcation
        notificationRepository.save(notification);

        // Sending email

        emailService.sendEmail(participant.getEmail(),
                subject,
                message);
    }
}
