package com.example.springmovie.service.interfaces;

public interface EncryptionService {
    String encryptPassword(String password);

    boolean verifyPassword(String password, String hash);
}
