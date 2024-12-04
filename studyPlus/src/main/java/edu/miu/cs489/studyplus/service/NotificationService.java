package edu.miu.cs489.studyplus.service;

import edu.miu.cs489.studyplus.dto.request.NotificationRequestDTO;
import edu.miu.cs489.studyplus.model.Participant;
import edu.miu.cs489.studyplus.model.Study;

public interface NotificationService {
    void sendNotification(Study study, Participant participant, String subject, String message);
}
