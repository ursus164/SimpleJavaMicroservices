package com.ursus.reciever.controller;

import com.ursus.reciever.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "course")
    public void notificationListener(Notification notification) {
        System.out.println(notification.toString());
    }
}
