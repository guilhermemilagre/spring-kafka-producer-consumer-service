package br.com.guilhermemilagre.kafkaservice.domain.service.impl;

import br.com.guilhermemilagre.kafkaservice.domain.model.Message;
import br.com.guilhermemilagre.kafkaservice.domain.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProducerServiceImpl implements ProducerService {

    @Value("${application.kafka.topics.notification}")
    private String topic;

    private final KafkaTemplate kafkaTemplate;

    @Override
    public Message producer(){
        String id = UUID.randomUUID().toString();
        var message = Message.builder().id(id).message("Message").build();
        kafkaTemplate.send(topic, id, message).completable().join();
        return message;
    }
}
