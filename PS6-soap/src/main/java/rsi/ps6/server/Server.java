package rsi.ps6.server;

import javax.xml.ws.Endpoint;

public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) {
        String host = args.length > 1 ? args[1] : "localhost";

        ImageService service = new ImageServiceImpl();

        String serviceName = ImageServiceImpl.class.getSimpleName();
        String address = "http://" + host + ":" + PORT + "/" + serviceName;
        Endpoint.publish(address, service);
        System.out.println(serviceName + " published at: " + address);
        String wsdlLocation = address + "?wsdl";
        System.out.println("Schema location: " + wsdlLocation);
    }
}
