package com.doconnect.doconnectservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*
 * @Author : Darshan Khairnar
 * Created Date : 26-8-2022
 * Modified Date : 27-8-2022
 * Description : Email Sender Service to send Email
 */
@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    /*
     * @Author : Darshan Khairnar
     * Created Date : 26-8-2022
     * Modified Date : 27-8-2022
     * Description : Sending Email to someone
     * Param : toEmail (String),body (String), Subject (String)
     */
    @Async
    public void sendMail(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshankhairnar73@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

    }

}
