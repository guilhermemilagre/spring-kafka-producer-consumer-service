package br.com.guilhermemilagre.kafkaservice.application.controller;


import br.com.guilhermemilagre.kafkaservice.domain.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("producer")
@Slf4j
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity producer(){

        log.debug("Messaging started");

        var message = producerService.producer();

        log.info("Message sent successfully, payload: {}", message);
        
        return ResponseEntity.accepted().build();
    }
}
