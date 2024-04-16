package rsi.ps5.server;

import javax.xml.ws.Endpoint;

public class Server {

    public static String ALLOWED_MAC;
    public static final int PORT = 9999;

    public static void main(String[] args) {
        ALLOWED_MAC = args[0];
        String host = args.length > 1 ? args[1] : "localhost";

        ShopInfoService service = new ShopInfoServiceImpl();
        AuthenticationService authenticationService = new AuthenticationServiceImpl();

        String serviceName = ShopInfoService.class.getSimpleName();
        String address = "http://" + host + ":" + PORT + "/" + serviceName;
        Endpoint.publish(address, service);
        System.out.println(serviceName + " published at: " + address);
        String wsdlLocation = address + "?wsdl";
        System.out.println("Schema location: " + wsdlLocation);

        serviceName = AuthenticationService.class.getSimpleName();
        address = "http://" + host + ":" + PORT + "/" + serviceName;
        Endpoint.publish(address, authenticationService);
        System.out.println(serviceName + " published at: " + address);
        wsdlLocation = address + "?wsdl";
        System.out.println("Schema location: " + wsdlLocation);
    }
}
