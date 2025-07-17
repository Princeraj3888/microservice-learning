package com.explorer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

    private final DynamoDbProperties properties;

    public DynamoDbConfig(DynamoDbProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DynamoDbClient dynamoDbClient(){
        System.out.println("properties loaded: accessKey: "+properties.getAccessKey()+" secret: "+properties.getSecretKey()+" endpoint url: "+properties.getEndpoint());
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                properties.getAccessKey(),
                properties.getSecretKey()
        );

        return DynamoDbClient.builder()
                .region(Region.AP_SOUTHEAST_2)
                .endpointOverride(URI.create(properties.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }
}
