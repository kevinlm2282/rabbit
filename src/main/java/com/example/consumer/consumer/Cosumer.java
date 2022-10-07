package com.example.consumer.consumer;

import com.example.consumer.dto.MessageDto;
import com.example.consumer.rabbitmw.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Cosumer {
    public static final Logger logger = Logger.getLogger(Cosumer.class.getName());
    @RabbitListener(queues = {RabbitMqConfig.queue+"1"})
    public void consumeMessageQueue1(MessageDto message) {
        if(message.getMessage().length() > 10){
           throw new RuntimeException("Message too long");
        }
        logger.info("Message received on Queue1: " + message);

    }

    @RabbitListener(queues = {RabbitMqConfig.queue+"2"})
    public void consumeMessageQueue2(MessageDto message) {
        if(message.getMessage().isEmpty()){
            throw new RuntimeException("Message is empty");
        }
        logger.info("Message received on Queue2: " + message);

    }

    @RabbitListener(queues = {RabbitMqConfig.queue+"3"})
    public void consumeMessageQueue3(MessageDto message) {
        if(message.getMessage().length() < 10){
            throw new RuntimeException("Message too short");
        }
        logger.info("Message received on Queue3: " + message);

    }
}
