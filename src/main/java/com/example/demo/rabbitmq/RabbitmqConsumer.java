package com.example.demo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConsumer {
    @Autowired
    private AmqpTemplate template;
    
    public void consumer() {
    	Message message=  template.receive("queue");
    	System.out.println("接收queue_noqeue的消息："+message.toString());
    }
}