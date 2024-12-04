package edu.miu.cs489.studyplus.service.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class NotificationServiceTest {

    @Autowired
    private  JavaMailSender mailSender;


    @Test
    public void testSendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sue.mwemeke@gmail.com");
        message.setSubject("Test 112");
        message.setText("This is a test");

        mailSender.send(message);

    }
}
