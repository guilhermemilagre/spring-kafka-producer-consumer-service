package br.com.guilhermemilagre.kafkaservice.domain.service;

import br.com.guilhermemilagre.kafkaservice.domain.model.Message;

public interface ProducerService {
    Message producer();
}
