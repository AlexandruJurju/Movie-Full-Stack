package com.example.backend.services.implementation;

import com.example.backend.enums.ImageType;
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
// todo: remove LocalImageFileService, add delete and download methods here
public class LocalFileService implements FileService {

    @Value("${static.resource.path}")
    String staticResourcePath;

    @Override
    public String upload(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(staticResourcePath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        String fullPath = staticResourcePath + "\\" + key;
        file.transferTo(new File(fullPath));
        return fullPath;
    }

    public String uploadWithType(MultipartFile file, ImageType type) throws IOException {

        Path uploadPath = Paths.get(staticResourcePath);

        if (type == ImageType.PERSON_PROFILE) {
            uploadPath = Paths.get(staticResourcePath + "images/casts/");
        } else if (type == ImageType.MOVIE_POSTER) {
            uploadPath = Paths.get(staticResourcePath + "images/movies/");
        } else if (type == ImageType.USER_PROFILE)
            uploadPath = Paths.get(staticResourcePath + "images/users/");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        String fullPath = staticResourcePath + "\\" + key;
        file.transferTo(new File(fullPath));
        return fullPath;
    }
}
