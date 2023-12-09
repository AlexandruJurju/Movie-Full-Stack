package com.example.backend.services.implementation;

import com.example.backend.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class LocalFileService implements FileService {

    // TODO: use absolute paths
    private static final String FOLDER_PATH = "X:\\Private Repos\\Movie Full-Stack\\upload-dir";

    @Override
    public String upload(MultipartFile file) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + filenameExtension;
        String fullPath = FOLDER_PATH + "\\" + key;
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Exception occurred while uploading the file");
        }
        return fullPath;
    }
}
