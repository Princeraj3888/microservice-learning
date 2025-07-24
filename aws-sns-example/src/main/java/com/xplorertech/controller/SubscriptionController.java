package com.xplorertech.controller;

import com.xplorertech.service.NotificationService;
import com.xplorertech.service.SnsSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SnsSubscriptionService subscriptionService;

    @Value("${cloud.aws.end-point.uri}")
    private String topicArn;

    @GetMapping("/notify")
    public String notifyService(){
        String subject = "Spring Boot SNS Email Test";
        String body = "Hello Raj! This is a test email from AWS SNS via Spring Boot.";

        notificationService.sendEmailNotification(topicArn, subject, body);

        return "Notification sent";
    }

    @GetMapping("/subscription/{email}")
    public String addSubscription(@PathVariable String email){
        subscriptionService.subscribeEmail(topicArn, email);

        return "subscription request send";
    }
}
