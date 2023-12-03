package com.example.backend.services.fileService;

import com.example.backend.entities.Image;
import com.example.backend.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service

@RequiredArgsConstructor
public class ImageService {

    private final LocalFileService fileService;
    private final ImageRepository imageRepository;

    public void uploadImage(MultipartFile file) throws IOException {
        String path = fileService.upload(file);
        Image image = new Image();
        image.setImagePath(path);
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        imageRepository.save(image);
    }
}


