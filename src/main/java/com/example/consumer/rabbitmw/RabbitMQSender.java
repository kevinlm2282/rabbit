package com.example.consumer.rabbitmw;

import com.example.consumer.dto.MessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private TopicExchange topicExchange;

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox","lazy.orange.rabbit"};
    public void send(MessageDto message) {

        String key = keys[message.getKey()];
        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange,key, message);
    }
}
