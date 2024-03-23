package rsi.ps4.services;

import javax.jws.WebService;

@WebService(endpointInterface = "rsi.ps4.services.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(String name) {
        System.out.println("Received parameter: " + name);
        return "Hello from JAX-WS: " + name;
    }
}
