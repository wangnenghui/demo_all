package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.rabbitmq.HelloSender;
import com.example.demo.rabbitmq.RabbitmqConsumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitMQ {
    
    @Autowired
    private HelloSender helloSender;
    @Autowired
    private RabbitmqConsumer rabbitmqConsumer;

    @Test
    public void testSendRabbit() {
        helloSender.send();
    }
    @Test
    public void testSendRabbit1() {
    	helloSender.send1();
    }
    @Test
    public void testConsummerRabbit() {
    	rabbitmqConsumer.consumer();
    }
}