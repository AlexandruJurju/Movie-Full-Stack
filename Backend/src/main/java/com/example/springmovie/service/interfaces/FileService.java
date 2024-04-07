package com.example.springmovie.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file);

    void delete(String path);

    byte[] download(String path);
}
