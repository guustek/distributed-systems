package rsi.ps5.server;

import javax.xml.ws.Endpoint;

public class Server {

    public static final int PORT = 9999;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";

        ShopInfoService service = new ShopInfoServiceImpl();

        String serviceName = ShopInfoService.class.getSimpleName();
        String address = "http://" + host + ":" + PORT + "/" + serviceName;
        Endpoint.publish(address, service);
        System.out.println(serviceName + " published at: " + address);
        String wsdlLocation = address + "?wsdl";
        System.out.println("Schema location: " + wsdlLocation);
    }
}
