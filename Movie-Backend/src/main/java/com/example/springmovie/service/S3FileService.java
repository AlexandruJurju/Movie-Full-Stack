package com.example.springmovie.service;

import com.example.springmovie.service.interfaces.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Slf4j
@Service
public class S3FileService implements FileService {

    private final S3Client s3Client;
    @Value("${s3.bucket.name}")
    private String bucket;
//    @Value("${spring.cloud.aws.region.static}")
//    private String region;
    private final S3Utilities s3Utilities;

    public S3FileService(S3Client s3Client) {
        this.s3Client = s3Client;
        s3Utilities = s3Client.utilities();
    }

    @Override
    public String upload(MultipartFile file) {
        log.info("Uploading file to S3");
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
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

        log.info("File uploaded successfully to S3");
        return s3Utilities.getUrl(GetUrlRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build())
                .toString();
    }

    public void delete(String path) {
        log.info("Deleting file from S3 with url {}", path);
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(getKeyFromUrl(path)).
                build());
        log.info("File deleted successfully from S3");
    }

    public byte[] download(String path) {
        log.info("Downloading file from S3 with url {}", path);
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucket)
                .key(getKeyFromUrl(path))
                .build());
        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getKeyFromUrl(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            return url.getPath().substring(1);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL", e);
        }
    }
}
