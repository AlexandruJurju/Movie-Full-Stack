package com.example.backend.services.implementation;

import com.example.backend.services.FileService;
import io.awspring.cloud.s3.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3FileService implements FileService {

    private String bucket = "movie-fullstack";
    private final S3Client s3Client;

    @Override
    public String upload(MultipartFile file) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        ObjectMetadata metadata = new ObjectMetadata.Builder().contentType(file.getContentType()).build();

        try {
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build(),
                    RequestBody.fromBytes(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return key;
    }
}
