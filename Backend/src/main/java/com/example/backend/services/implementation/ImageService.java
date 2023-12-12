package com.example.backend.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3FileService fileService;

    public String upload(MultipartFile file) {
        return fileService.upload(file);
    }

}
