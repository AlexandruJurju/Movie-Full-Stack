package com.example.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Movie REST API for full stack application",
        description = "A backend rest api program for a full stack project",
        version = "v1",
        contact = @Contact(
                name = "Alexandru Jurju",
                email = "alexandru.i.jurju@gmail.com"
        )
),
        servers = @Server(url = "http://localhost:8080"))

public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
