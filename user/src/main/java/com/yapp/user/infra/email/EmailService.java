package com.yapp.user.infra.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    public void sendMail(String email, String subject, String text) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom(sender);
        simpleMessage.setTo(email);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(text);
        javaMailSender.send(simpleMessage);
    }

    @Async
    public void sendMailToAmdin(String subject, String text) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom(sender);
        simpleMessage.setTo(sender);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(text);
        javaMailSender.send(simpleMessage);
    }

}

