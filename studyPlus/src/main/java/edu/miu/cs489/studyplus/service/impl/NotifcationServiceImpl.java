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

@Service
@RequiredArgsConstructor
public class NotifcationServiceImpl implements NotificationService {
    private final ParticipantRepository participantRepository;
    private final StudyRepository studyRepository;
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationRequestDTO requestDTO) {

    // Fetch Study name
        Study study = studyRepository.findStudyByStudyName(requestDTO.studyRequestDTO().studyName())
                .orElseThrow(() -> new EntityNotFoundException("Study not found"));

        // Fetch Participant name
        Participant participant = participantRepository.findParticipantByUsername(requestDTO.participantRequestDTO().username())
                .orElseThrow(() -> new UserNotFoundException("Participant not found"));

        // Create notification
        Notification notification = new Notification();
        notification.setStudy(study);
        notification.setParticipant(participant);
        notification.setSubject(requestDTO.subject());
        notification.setMessage(requestDTO.message());
        notification.setSentAt(requestDTO.sentAt());

        // save notifcation
        notificationRepository.save(notification);

        // Sending email
        String emailMessage = String.format(
                "Dear %s,%n%n%s%n%nStudy: %s%n%nRegards,%nStudy Team",
                participant.getUsername(),
                requestDTO.message(),
                study.getStudyName()
        );
        emailService.sendEmail(participant.getEmail(),
                requestDTO.subject(),
                emailMessage);
    }
}
