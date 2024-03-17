package rsi.ps3.server;

import javax.jws.WebService;

@WebService(endpointInterface = "rsi.ps3.server.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(String name) {
        System.out.println("Received parameter: " + name);
        return "Hello from JAX-WS: " + name;
    }
}
