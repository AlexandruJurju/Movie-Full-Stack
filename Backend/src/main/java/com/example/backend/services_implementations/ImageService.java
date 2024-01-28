package com.example.backend.services_implementations;

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

    public byte[] download(String key) {
        return fileService.download(key);
    }

    // delete all is just loop over all movies and call delete for that poster url
    public void delete(String key) {
        fileService.delete(key);
    }

}
