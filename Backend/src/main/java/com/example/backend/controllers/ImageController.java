package com.example.backend.controllers;

import com.example.backend.services.fileService.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/image")

@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            imageService.uploadImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
