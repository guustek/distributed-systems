package rsi.ps2.cw1;

import java.rmi.RemoteException;

import rsi.ps2.RmiServer;

public class Server {
    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServer();
        ProductRepository object = new ProductRepositoryImpl();
        rmiServer.registerObject(object, ProductRepository.REMOTE_NAME);

        while(true) {}
    }
}
