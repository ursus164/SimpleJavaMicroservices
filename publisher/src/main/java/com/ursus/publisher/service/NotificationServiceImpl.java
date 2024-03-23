package com.ursus.publisher.service;

import com.ursus.publisher.model.Notification;
import com.ursus.publisher.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {
    public static final String URL_STUDENT_SERVICE = "http://localhost:8070/students/";
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    public NotificationServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendStudentNotification(long studentId) {
        // Send notification to student
        Student student = restTemplate.exchange(URL_STUDENT_SERVICE + studentId,
                HttpMethod.GET, HttpEntity.EMPTY, Student.class).getBody();

        Notification notification = getNotification(student);

        rabbitTemplate.convertAndSend("course", notification);
    }

    private static Notification getNotification(Student student) {
        Notification notification = new Notification();

        notification.setEmail(student.getEmail());
        notification.setTitle("Hello " + student.getName());
        notification.setBody("We have a good news for you, " + student.getName() + " " + student.getSurname() + "!");
        return notification;
    }
}
