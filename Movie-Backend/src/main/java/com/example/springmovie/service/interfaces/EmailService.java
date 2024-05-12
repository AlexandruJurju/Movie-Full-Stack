package com.example.springmovie.service.interfaces;

public interface EmailService {
    void sendMail(String to, String subject, String text);
}
