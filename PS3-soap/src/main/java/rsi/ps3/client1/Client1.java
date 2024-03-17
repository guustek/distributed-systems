package rsi.ps3.client1;

import rsi.ps3.client1.generated.HelloWorld;
import rsi.ps3.client1.generated.HelloWorldImplService;

public class Client1 {
    public static void main(String[] args) {
        HelloWorldImplService client = new HelloWorldImplService();
        HelloWorld port = client.getHelloWorldImplPort();

        String response = port.getHelloWorldAsString(Client1.class.getSimpleName());
        System.out.println("Response: " + response);
    }
}
