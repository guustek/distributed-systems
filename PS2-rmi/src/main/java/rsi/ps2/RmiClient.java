package rsi.ps2;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    private final Registry remoteRegistry;
    private final String remoteRegistryUrl;

    public RmiClient(String host, boolean useSSL) throws RemoteException {
        String machineAddress = HostUtils.getMachineAddress();
        System.out.println("Connecting to remote registry from " + machineAddress);
        System.setProperty("java.rmi.server.hostname", machineAddress);
        //System.setProperty("javax.net.debug", "all");
        int port = Registry.REGISTRY_PORT;
        //get remote registry
        this.remoteRegistryUrl = "rmi://" + host + ":" + port;
        System.out.println("Looking for remote registry at: " + remoteRegistryUrl);
        if(useSSL) {
            String password = "password";
            //System.setProperty("javax.net.ssl.debug", "all");
            System.setProperty("javax.net.ssl.trustStore", "truststore.jts");
            System.setProperty("javax.net.ssl.trustStorePassword", password);
            this.remoteRegistry = LocateRegistry.getRegistry(host, port, new SslRMIClientSocketFactory());
        } else {
            this.remoteRegistry = LocateRegistry.getRegistry(host, port);
        }
    }

    public <T extends Remote> T getRemoteObject(String name) throws NotBoundException, RemoteException {
        String remoteObjectUrl = remoteRegistryUrl + "/" + name;
        System.out.println("Looking for remote object at: " + remoteObjectUrl);
        return (T) remoteRegistry.lookup(name);
    }
}
