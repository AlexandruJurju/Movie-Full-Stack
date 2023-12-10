package com.example.backend.services.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor

@Service
public class LocalImageService {

    private final LocalFileService fileService;

    public String uploadImage(MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

    public byte[] downloadImage(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

    public void deleteImage(String path) throws IOException {
        Files.delete(new File(path).toPath());
    }
}
