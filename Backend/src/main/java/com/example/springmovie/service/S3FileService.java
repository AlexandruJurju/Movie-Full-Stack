package com.example.springmovie.service;

import com.example.springmovie.service.interfaces.FileService;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class S3FileService implements FileService {

    @Value("${s3.bucket.name}")
    private String bucket;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    private S3Utilities s3Utilities;

    private final S3Client s3Client;

    private static final Logger log = Logger.getLogger(S3FileService.class.getName());

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
            log.log(Level.SEVERE, "Error occurred while uploading file to S3", e);
            throw new RuntimeException(e);
        }

        log.info("File uploaded successfully to S3");
        return s3Utilities.getUrl(GetUrlRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build())
                .toString();
    }

    public void delete(String fileUrl) {
        log.info("Deleting file from S3 with url " + fileUrl);
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(getKeyFromUrl(fileUrl)).
                build());
        log.info("File deleted successfully from S3");
    }

    public byte[] download(String fileUrl) {
        log.info("Downloading file from S3 with url " + fileUrl);
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucket)
                .key(getKeyFromUrl(fileUrl))
                .build());
        try {
            return response.readAllBytes();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error occurred while downloading file from S3", e);
            throw new RuntimeException(e);
        }
    }

    private String getKeyFromUrl(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            return url.getPath().substring(1);
        } catch (MalformedURLException e) {
            log.log(Level.SEVERE, "Invalid URL", e);
            throw new RuntimeException("Invalid URL", e);
        }
    }
}
