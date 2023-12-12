package com.example.backend.services.implementation;

import com.example.backend.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LocalFileService implements FileService {

    @Value("${resource.path}")
    String resourceFolder;

    @Override
    public String upload(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(resourceFolder);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        String fullPath = resourceFolder + "\\" + key;
        file.transferTo(new File(fullPath));
        return key;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        String fullPath = resourceFolder + "\\" + fileName;
        return Files.readAllBytes(new File(fullPath).toPath());
    }

    public void deleteImage(String fileName) throws IOException {
        String fullPath = resourceFolder + "\\" + fileName;
        Files.delete(new File(fullPath).toPath());
    }

}
