package org.example.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        property("jersey.config.server.wadl.disableWadl", false);  // Enable WADL generation
    }
}