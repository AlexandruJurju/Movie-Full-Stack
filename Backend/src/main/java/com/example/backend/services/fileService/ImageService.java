package com.example.backend.services.fileService;

import com.example.backend.entities.Image;
import com.example.backend.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

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

    public byte[] downloadImage(Long id) throws IOException {
        Optional<Image> image = imageRepository.findById(id);
        String fullPath = image.get().getImagePath();
        return Files.readAllBytes(new File(fullPath).toPath());
    }

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

}


