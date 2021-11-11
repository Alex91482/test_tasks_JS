package com.example.hospitalregistration.service.mail;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService{

    private static final Logger logger = LogManager.getLogger(EmailService.class);

    public SimpleMailMessage sendMail(String patientMail, Date dateOfVisit, String pass, String login){ //для составления письма понадобится адрес получателя и дата когда будет прием

        SimpleMailMessage message = new SimpleMailMessage();
        try{
            message.setTo(patientMail);                             //почта получателя
            message.setSubject(MyConstants.STANDARD_THEME);         //тема сообщения
            message.setText(MyConstants.STANDARD_MESSAGE + dateOfVisit.toString()
                    + MyConstants.SANDARD_TELLMELOG + login
                    + MyConstants.SANDARD_TELLMEPASS + pass); //текст сообщения
            return message;
        }catch (Exception e){
            logger.warn(e.getMessage());
        }

        return message;
    }
}

