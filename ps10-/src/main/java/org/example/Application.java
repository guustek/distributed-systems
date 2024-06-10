package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // http://localhost:8080/message
    // http://localhost:8080/message/1
    
    // http://localhost:8080/message + postman tworzenie post...
    // to samo z put
    // to samo z delete
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
