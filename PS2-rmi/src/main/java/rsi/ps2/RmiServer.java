package rsi.ps2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    private final Registry registry;
    private final String registryUrl;

    public RmiServer() throws RemoteException {
        String host = getMachineAddress();
        int registryPort = Registry.REGISTRY_PORT;

        //create registry
        System.setProperty("java.rmi.server.hostname", host);
        this.registry = LocateRegistry.createRegistry(registryPort);
        this.registryUrl = "rmi://" + host + ":" + registryPort;
        System.out.println("RMI registry available at: " + registryUrl);
    }

    public void registerObject(Remote remote, String name) throws RemoteException {
        registry.rebind(name, remote);
        System.out.println("Registered object under " + registryUrl + "/" + name);
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
