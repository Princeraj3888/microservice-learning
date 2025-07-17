package com.explorer.config;

import com.explorer.model.AwsSecrets;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    private Gson gson = new Gson();
    @Bean
    public DataSource dataSource(){
        AwsSecrets secrets = getSecret();
       return DataSourceBuilder.create()
                //.driverClassName("")
                .url("jdbc:"+secrets.getEngine()+"://"+secrets.getHost()+":"+secrets.getPort()+"/explorertech")
                .username(secrets.getUsername())
                .password(secrets.getPassword())
                .build();
    }

    private AwsSecrets getSecret() {

        String secretName = "exploretech-db-credential";
        Region region = Region.of("ap-southeast-2");

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }

        String secret = getSecretValueResponse.secretString();

        return gson.fromJson(secret, AwsSecrets.class);
    }
}
