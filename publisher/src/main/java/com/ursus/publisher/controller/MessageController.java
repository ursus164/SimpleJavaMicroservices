package com.ursus.publisher.controller;

import com.ursus.publisher.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class MessageController {
    private final NotificationService notificationService;

    public MessageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String sendNotification(@RequestParam long id) {
        notificationService.sendStudentNotification(id);
        return "Notification to student with id: " + id + " has been sent.";
    }
}
