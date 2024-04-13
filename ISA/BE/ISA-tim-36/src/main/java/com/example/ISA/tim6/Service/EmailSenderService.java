package com.example.ISA.tim6.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body)
    {
        SimpleMailMessage newMail = new SimpleMailMessage();
        newMail.setFrom("lukapikula13@gmail.com");
        newMail.setTo(toEmail);
        newMail.setText(body);
        newMail.setSubject(subject);

        mailSender.send(newMail);

        System.out.println("Mail poslat uspesno!");
    }
}
