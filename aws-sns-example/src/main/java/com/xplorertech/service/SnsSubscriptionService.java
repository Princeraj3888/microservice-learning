package com.xplorertech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;

@Service
@RequiredArgsConstructor
public class SnsSubscriptionService {

    @Autowired
    private SnsClient snsClient;

    public String subscribeEmail(String topicArn, String email) {
        SubscribeRequest request = SubscribeRequest.builder()
                .protocol("email")
                .endpoint(email)  // Email address to subscribe
                .topicArn(topicArn)
                .returnSubscriptionArn(true)
                .build();

        SubscribeResponse response = snsClient.subscribe(request);

        return "Subscription requested. Check your email to confirm. ARN: " + response.subscriptionArn();
    }
}
