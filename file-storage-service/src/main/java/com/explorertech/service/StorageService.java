package com.explorertech.service;

import com.explorertech.config.StorageConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;
import java.util.List;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    private final S3Client s3Client;

    public StorageService(S3Client s3Client){
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        try {
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
            uploadFile(fileName,
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType());
            fileObj.delete();
        } catch (IOException e) {
            System.out.println("failed to upload file: "+e.getMessage());
            e.printStackTrace();
        }
        return "file uploaded successfully";
    }

    // Download
    public File getFile(String fileName) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        try (ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(request)) {
            File tempFile = File.createTempFile("s3-", "-" + fileName);
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                s3Object.transferTo(outputStream);
            }
            return tempFile;
        }
    }

    // Delete
    public String deleteFile(String fileName) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.deleteObject(request);
        return "file delete successfully";
    }
    public List<String> listObjects(String prefix){
        ListObjectsV2Request.Builder requestBuilder = ListObjectsV2Request.builder()
                .bucket(bucketName);

        if(prefix != null && !prefix.isEmpty()){
            requestBuilder.prefix(prefix);
        }

        ListObjectsV2Response response = s3Client.listObjectsV2(requestBuilder.build());
        List<S3Object> objects = response.contents();

        return objects.stream().map(S3Object::key)
                .toList();
    }

    public void uploadFile(String key, File file){
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
    }

    public void close(){
        s3Client.close();
    }

    public void uploadFile(String key, InputStream inputStream, long contentLength, String contentType){
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentLength(contentLength)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, contentLength));
    }

    private File convertMultiPartFileToFile(MultipartFile file){
        File convertFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertFile)){
            fos.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertFile;
    }

}
