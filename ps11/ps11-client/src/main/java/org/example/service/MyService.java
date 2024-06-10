package org.example.service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    
    private final Client jaxRsClient;
    
    public MyService(Client jaxRsClient) {
        this.jaxRsClient = jaxRsClient;
    }
    
    public String getMessageFromService(int value) {
        Response response = jaxRsClient.target("http://localhost:8080/message/" + value)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
        }
    }
    
    public String postMessageToService(Message message) {
        Response response = jaxRsClient.target("http://localhost:8080/message")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(message));
        
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
        }
    }
    
    public String getMessagesFromService() {
        Response response = jaxRsClient.target("http://localhost:8080/json")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
        }
    }
    
    public String getMessageStartsWithPrefix(String prefix) {
        Response response = jaxRsClient.target("http://localhost:8080/message-prefix")
                .queryParam("prefix", prefix)
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
        }
    }
}