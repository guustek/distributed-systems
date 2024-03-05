package rsi.ps2;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    private final Registry remoteRegistry;
    private final String remoteRegistryUrl;

    public RmiClient(String host) throws RemoteException {
        int port = Registry.REGISTRY_PORT;

        //get remote registry
        this.remoteRegistryUrl = "rmi://" + host + ":" + port;
        System.out.println("Looking for remote registry at: " + remoteRegistryUrl);
        this.remoteRegistry = LocateRegistry.getRegistry(host, port);
    }

    public <T extends Remote> T getRemoteObject(String name) throws NotBoundException, RemoteException {
        String remoteObjectUrl = remoteRegistryUrl + "/" + name;
        System.out.println("Looking for remote object at: " + remoteObjectUrl);
        return (T) remoteRegistry.lookup(name);
    }
}
