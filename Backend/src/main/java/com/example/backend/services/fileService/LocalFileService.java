package com.example.backend.services.fileService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class LocalFileService implements FileService {

    // TODO: use absolute paths
    private static final String FOLDER_PATH = "X:\\Private Repos\\Movie Full-Stack\\Backend\\src\\main\\images";

    @Override
    public String upload(MultipartFile file) throws IOException {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID().toString() + "." + filenameExtension;
        String fullPath = FOLDER_PATH + "\\" + key;
        file.transferTo(new File(fullPath));
        return fullPath;
    }
}
