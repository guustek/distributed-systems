package rsi.ps3.client2;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import rsi.ps3.HostUtils;
import rsi.ps3.server.HelloWorld;
import rsi.ps3.server.HelloWorldImpl;
import rsi.ps3.server.Server;

public class Client2 {
    public static void main(String[] args) throws MalformedURLException {
        String host = args.length > 0 ? args[0] : HostUtils.getMachineAddress();

        String serviceName = HelloWorld.class.getSimpleName();
        String wsdl = "http://" + host + ":" + Server.PORT + "/" + serviceName + "?wsdl";
        URL url = new URL(wsdl);

        String targetNamespace = "http://server.ps3.rsi/";
        String localPart = HelloWorldImpl.class.getSimpleName() + "Service";
        QName qName = new QName(targetNamespace, localPart);

        Service client = Service.create(url, qName);

        HelloWorld helloPort = client.getPort(HelloWorld.class);

        String response = helloPort.getHelloWorldAsString(Client2.class.getSimpleName());
        System.out.println("Response: " + response);
    }
}
