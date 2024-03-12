package rsi.ps2;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    private final Registry registry;
    private final String registryUrl;

    public RmiServer(boolean useSSL) throws RemoteException {
        String host = HostUtils.getMachineAddress();
        int port = Registry.REGISTRY_PORT;

        //create registry
        System.setProperty("java.rmi.server.hostname", host);
        //System.setProperty("javax.net.debug", "all");
        if (useSSL) {
            String password = "password";
            //System.setProperty("javax.net.ssl.debug", "all");
            System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", password);
            this.registry = LocateRegistry.createRegistry(
                    port,
                    new SslRMIClientSocketFactory(),
                    new SslRMIServerSocketFactory());
        } else {
            this.registry = LocateRegistry.createRegistry(port);
        }
        this.registryUrl = "rmi://" + host + ":" + port;
        System.out.println("RMI registry available at: " + registryUrl);
    }

    public void registerObject(Remote remote, String name) throws RemoteException {
        registry.rebind(name, remote);
        System.out.println("Registered object under " + registryUrl + "/" + name);
    }

}
