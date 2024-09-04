package com.artsem.api.coreservice.service.impl;

import com.artsem.api.coreservice.service.MessageSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSenderServiceImpl implements MessageSenderService {

    private final AmqpTemplate amqpTemplate;

    @Value("${queue.name}")
    private String queueName;

    public void send(String message) {
        amqpTemplate.convertAndSend(queueName, message);
    }

}
