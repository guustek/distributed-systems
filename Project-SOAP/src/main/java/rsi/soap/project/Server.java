package rsi.soap.project;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.*;
import java.security.*;
import java.security.cert.CertificateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import rsi.soap.project.repositories.CarRepository;
import rsi.soap.project.repositories.CarRepositoryImpl;
import rsi.soap.project.repositories.ReservationRepository;
import rsi.soap.project.repositories.ReservationRepositoryImpl;
import rsi.soap.project.services.CarRentalService;
import rsi.soap.project.services.CarRentalServiceImpl;

public class Server {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void main(String[] args) throws IOException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException {
        String host = getMachineAddress();
        int httpPort = 8080;
        int httpsPort = 8443;

        CarRepository carRepository = new CarRepositoryImpl();
        ReservationRepository reservationRepository = new ReservationRepositoryImpl();
        CarRentalService service = new CarRentalServiceImpl(carRepository, reservationRepository);

        // HTTP configuration
        String httpAddress = "http://" + host + ":" + httpPort + "/CarRentalService";
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(host, httpPort), 0);
        Endpoint httpEndpoint = Endpoint.create(SOAPBinding.SOAP11HTTP_BINDING, service);
        httpEndpoint.publish(httpServer.createContext("/CarRentalService"));
        httpServer.start();
        System.out.println("HTTP");
        System.out.println("CarRentalService service published at: " + httpAddress);
        System.out.println("Schema location: " + httpAddress + "?wsdl");

        System.out.println();

        // HTTPS configuration
        char[] password = "password".toCharArray();
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(Files.newInputStream(Paths.get("keystore.jks")), password);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        String httpsAddress = "https://" + host + ":" + httpsPort + "/CarRentalService";
        HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(host, httpsPort), 0);
        httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        Endpoint httpsEndpoint = Endpoint.create(SOAPBinding.SOAP11HTTP_BINDING, service);
        httpsEndpoint.publish(httpsServer.createContext("/CarRentalService"));
        httpsServer.start();
        System.out.println("HTTPS");
        System.out.println("CarRentalService service published at: " + httpsAddress);
        System.out.println("Schema location: " + httpsAddress + "?wsdl");

        System.out.println();
    }

    public static String readFile(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeToFile(Path path, String payload) {
        try {
            Files.write(path.toAbsolutePath(), payload.getBytes());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * @return  Hostname/IP Address of local machine based on network interface used for internet connections
     */
    private static String getMachineAddress() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            return socket.getLocalAddress().getHostAddress();
        } catch (IOException ex) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return "localhost";
            }
        }
    }
}
