package com.doconnect.doconnectservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender javaMailSender;


    @Async
    public void sendMail(String toEmail,String body,String subject)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshankhairnar73@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

    }
    
}
