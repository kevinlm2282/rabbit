package com.example.consumer.api;

import com.example.consumer.dto.MessageDto;
import com.example.consumer.rabbitmw.RabbitMQSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MessageApi {

    private RabbitMQSender rabbitMQSender;
    public static Logger logger = LoggerFactory.getLogger(MessageApi.class);
    @Autowired
    public MessageApi(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody MessageDto messageDto){
        messageDto.setDate(new Date(System.currentTimeMillis()));
        logger.info("Message sent: " + messageDto);
        messageDto.setId(UUID.randomUUID().hashCode());
        rabbitMQSender.send(messageDto);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
