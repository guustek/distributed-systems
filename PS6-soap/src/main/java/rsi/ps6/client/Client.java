package rsi.ps6.client;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.MTOMFeature;

import java.util.Map;

import rsi.ps6.client.generated.imageservice.ImageService;
import rsi.ps6.client.generated.imageservice.ImageServiceImpl;

public class Client {

    public static void main(String[] args) {

        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);

        System.setProperty("javax.net.ssl.trustStore", "PS6-soap/src/main/java/rsi/ps6/client/client_cacerts.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        ImageServiceImpl client = new ImageServiceImpl();
        ImageService port = client.getImageServiceImplPort(new MTOMFeature());

        BindingProvider bindProv = (BindingProvider) port;
        Map<String, Object> context = bindProv.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://localhost:8443/PS6-soap/ImageServiceImpl");

        byte[] bytes = port.downloadImage("logo_pb.png");

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JLabel label = new JLabel(new ImageIcon(bytes));
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
