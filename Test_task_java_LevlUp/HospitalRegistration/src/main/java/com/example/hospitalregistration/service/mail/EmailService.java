package com.example.hospitalregistration.service.mail;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService{

    @Autowired
    public JavaMailSender emailSender;

    private static final Logger logger = LogManager.getLogger(EmailService.class);

    public void sendMail(String patientMail, Date dateOfVisit){
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(patientMail);                             //почта получателя
            message.setSubject(MyConstants.STANDARD_THEME);         //тема сообщения
            message.setText(MyConstants.STANDARD_MESSAGE + dateOfVisit.toString()); //текст сообщения
            this.emailSender.send(message);

        }catch (Exception e){
            logger.warn(e.getMessage());
        }
    }
}

