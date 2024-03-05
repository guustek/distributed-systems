package rsi.ps1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException, AlreadyBoundException {
        String host = getMachineAddress();
        int registryPort = Registry.REGISTRY_PORT;

        //create registry
        System.setProperty("java.rmi.server.hostname", host);
        Registry registry = LocateRegistry.createRegistry(registryPort);
        String registryUrl = "rmi://" + host + ":" + registryPort;
        System.out.println("Registry available at: " + registryUrl);

        //bind object
        Calculator calculator = new CalculatorImpl();
        registry.bind(Calculator.REMOTE_NAME, calculator);
        System.out.println("Registered object under " + registryUrl + "/" + Calculator.REMOTE_NAME);

        System.out.println("Registered names: " + Arrays.toString(registry.list()));
        System.out.println();
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