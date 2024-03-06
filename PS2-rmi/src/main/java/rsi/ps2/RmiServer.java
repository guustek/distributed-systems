package rsi.ps2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    private final Registry registry;
    private final String registryUrl;

    public RmiServer() throws RemoteException {
        String host = HostUtils.getMachineAddress();
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

}
