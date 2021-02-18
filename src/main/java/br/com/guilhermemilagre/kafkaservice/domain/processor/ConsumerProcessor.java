package br.com.guilhermemilagre.kafkaservice.domain.processor;

import br.com.guilhermemilagre.kafkaservice.domain.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "application.kafka.listener", name = "enable", havingValue = "true")
public class ConsumerProcessor {

    @Value("${application.kafka.topics.history}")
    private String topic;

    private final KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "${application.kafka.topics.notification}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(@Payload Message message,
                         final ConsumerRecord consumerRecord) {
        log.debug("key: " + consumerRecord.key());
        log.debug("Headers: " + consumerRecord.headers());
        log.debug("Partion: " + consumerRecord.partition());
        kafkaTemplate.send(topic, message.getId(), message).completable().join();
        log.info("Message consumed successfully, payload: {}", message);
    }

}
