package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // http://localhost:8080/hello
    // http://localhost:8080/echo
    // http://localhost:8080/echo/123
    // http://localhost:8080/xml
    // http://localhost:8080/json
    // + postman show endpoint
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
