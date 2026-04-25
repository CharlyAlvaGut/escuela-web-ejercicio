package com.ipn.escuela_web.features.email.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ipn.escuela_web.features.email.service.IEmailService;

@Service
public class EmailServiceImp implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorreo(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("jcarlosagutierrezipn@gmail.com");
        mailSender.send(message);
    }
}