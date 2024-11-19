package edu.miu.cs489.studyplus.service;

import edu.miu.cs489.studyplus.dto.request.NotificationRequestDTO;

public interface NotificationService {
    void sendNotification(NotificationRequestDTO requestDTO);
}
