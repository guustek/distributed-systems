package org.example.path;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getHello() {
        return "Witaj JAX-RS";
    }
    
    @GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getEcho() {
        return "Witaj echo";
    }
    
    @GetMapping(value = "/echo/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getEchoByParam(@PathVariable String name) {
        return "Witaj " + name;
    }
}
