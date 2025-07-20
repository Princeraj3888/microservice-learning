package com.explorer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.UUID;

@RestController
public class SqsService {

    @Autowired
    private SqsClient sqsClient;

    /*@Value("${cloud.aws.credentials.end-point.uri}")*/
    private final String sqsQueueUrl = "https://sqs.ap-southeast-2.amazonaws.com/796973505063/explorer-queue.fifo";

    @GetMapping("/send/{message}")
    public void sendMessage(@PathVariable String message){
        //System.out.println("sqsQueueUrl: "+);
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .messageBody(message)
                .messageGroupId("explore-group-id")
                .messageDeduplicationId(UUID.randomUUID().toString())
                .build();
        sqsClient.sendMessage(sendMessageRequest);
    }

    @GetMapping("/receive")
    public void receiveMessage(){
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .maxNumberOfMessages(5)
                .waitTimeSeconds(10)
                .build();

        sqsClient
                .receiveMessage(receiveMessageRequest).messages()
            .forEach(message -> {
                System.out.println("received: "+message.body());

                //optionally delete after processing
                sqsClient.deleteMessage(DeleteMessageRequest.builder()
                                .queueUrl(sqsQueueUrl)
                                .receiptHandle(message.receiptHandle())
                        .build());
            });
    }
}
