package org.example;

import org.example.model.Message;
import org.example.service.MyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    // http://localhost:8080/message
    // http://localhost:8080/message/1
    
    private final MyService myService;
    
    public Application(MyService myService) {
        this.myService = myService;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println();

        performExercise1();

        System.out.println();

        performExercise2();
        
    }
    
    private void performExercise1() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Zad 1: ");
        System.out.println("Wiadomosc id=1: " + myService.getMessageFromService(1));
        
        System.out.println("Wszystkie wiadomosci: " + myService.getMessagesFromService());
        
        Message message = new Message(1, "Nowa wiadomosc", "Client");
        System.out.println("Dodanie wiadomosc: " + myService.postMessageToService(message));
        
        System.out.println("Wszystkie wiadomosci: " + myService.getMessagesFromService());
    }
    
    private void performExercise2() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Zad 2: ");
        System.out.println("Zaczyna sie na \"Nowa\": " + myService.getMessageStartsWithPrefix("Nowa"));
    }
}
