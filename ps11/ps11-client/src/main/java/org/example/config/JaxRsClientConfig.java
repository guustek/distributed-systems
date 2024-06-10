package org.example.config;

import jakarta.ws.rs.client.Client;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaxRsClientConfig {
    
    @Bean
    public Client jaxRsClient() {
        return JerseyClientBuilder.createClient();
    }
}