package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // http://localhost:8080/message
    // http://localhost:8080/message/1
    
    // zad3 - http://localhost:8080/products
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
