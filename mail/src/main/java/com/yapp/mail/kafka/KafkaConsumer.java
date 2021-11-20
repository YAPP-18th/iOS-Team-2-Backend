package com.yapp.mail.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaConsumer {
    private final EmailService emailService;


    @KafkaListener(topics = "mail-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        String emailAddress = (String) map.get("emailAddress");
        String subject = (String) map.get("subject");
        String content = (String) map.get("content");
        if (emailAddress.equals(null)) {
            emailService.sendMailToAmdin(subject, content);
            return;
        }
        emailService.sendMail(emailAddress, subject, content);
    }
}
