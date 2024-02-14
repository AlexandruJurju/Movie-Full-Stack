package com.example.springmovie.service.impl;

import com.example.springmovie.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileService implements FileService {

    @Value("${s3.bucket.name}")
    private String bucket;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    private final S3Client s3Client;

    @Override
    public String upload(MultipartFile file) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        //        String key = file.getOriginalFilename();
        //        ObjectMetadata metadata = new ObjectMetadata.Builder().contentType(file.getContentType()).build();

        // Upload file to aws
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .acl(ObjectCannedACL.PUBLIC_READ)
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Call AWS to get the file url
        // 1'st option: return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        // TODO: entity should store the key, not the whole url
        /*
        "https://movie-fullstack.s3.eu-central-1.amazonaws.com/a210837a-64bd-4d56-8d9f-d99425bd6068.jpg" contains:
        Bucket name: movie-fullstack
        Region: eu-central-1
        Key: a210837a-64bd-4d56-8d9f-d99425bd6068.jpg
         */
        return s3Client.utilities().getUrl(request).toString();
    }

    public void delete(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key).
                build());
    }

    public byte[] download(String key) {
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());
        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
