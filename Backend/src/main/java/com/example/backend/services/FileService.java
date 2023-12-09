package com.example.backend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public String upload(MultipartFile file) throws IOException;
}
