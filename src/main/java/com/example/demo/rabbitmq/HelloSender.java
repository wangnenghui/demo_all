package com.example.demo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate template;
    
    public void send() {
    	template.convertAndSend("queue_","hello,rabbit~ww");
    }
    public void send1() {
    template.convertAndSend("exchange","topic.messages","hello,rabbit~ww");
    }
}