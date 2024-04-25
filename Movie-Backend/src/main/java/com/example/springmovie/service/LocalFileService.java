package com.example.springmovie.service;

import com.example.springmovie.service.interfaces.FileService;
import jakarta.annotation.Nonnull;
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

import static java.io.File.separator;


@Service
public class LocalFileService implements FileService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Override
    public String upload(@Nonnull MultipartFile file) {
        File targetFolder = new File(fileUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                return null;
            }
        }

        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        String finalFilePath = fileUploadPath + separator + key;

        Path targetPath = Paths.get(finalFilePath);
        try {
            Files.write(targetPath, file.getBytes());
            return finalFilePath;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void delete(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public byte[] download(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            // handle exception
            return new byte[0];
        }
    }
}
