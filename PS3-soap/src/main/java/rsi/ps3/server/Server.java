package rsi.ps3.server;

import javax.xml.ws.Endpoint;

import rsi.ps3.HostUtils;

public class Server {

    public static final int PORT = 9999;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";

        HelloWorld service = new HelloWorldImpl();

        String serviceName = HelloWorld.class.getSimpleName();
        String address = "http://" + host + ":" + PORT + "/" + serviceName;
        Endpoint.publish(address, service);
        System.out.println(serviceName + " service published at: " + address);
        String wsdlLocation = address + "?wsdl";
        System.out.println("Schema location: " + wsdlLocation);
    }
}