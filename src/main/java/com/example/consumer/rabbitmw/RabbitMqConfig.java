package com.example.consumer.rabbitmw;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;


@Configuration
public class RabbitMqConfig {
    public static final String queue = "queue";
    public static String exchange = "arqui_exchange";
    public static String routingKey = "arqui_routingKey";

    public static final String DEAD_LETTER_QUEUE = "dead_queue";
    public static String DEAD_LETTER_EXCHANGE = "dead_letter_exchange";
    public static String DEAD_LETTER_ROUTING_KEY = "dead_letter_routingKey";

    @Bean
    Queue queue1(){
        return  QueueBuilder.durable(queue+"1")
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
                .build();
    }
    @Bean
    Queue queue2(){
        return QueueBuilder.durable(queue+"2")
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
                .build();
    }
    @Bean
    Queue queue3(){
        return   QueueBuilder.durable(queue+"3")
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
                .build();
    }


//    @Bean
//    DirectExchange exchange(){
//        return new DirectExchange(exchange);
//    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding1(Queue queue1, TopicExchange topicExchange){
        return BindingBuilder.bind(queue1).to(topicExchange).with("*.orange.*");
    }

    @Bean
    Binding binding2(Queue queue2, TopicExchange topicExchange){
        return BindingBuilder.bind(queue2).to(topicExchange).with("*.*.rabbit");
    }
    @Bean
    Binding binding3(Queue queue3, TopicExchange topicExchange){
        return BindingBuilder.bind(queue3).to(topicExchange).with("lazy.#");
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }
    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange){
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
