package rsi.ps2.products;

import java.rmi.RemoteException;

import rsi.ps2.RmiServer;

class Server {
    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServer();
        ProductRepository object = new ProductRepositoryImpl();
        rmiServer.registerObject(object, ProductRepository.REMOTE_NAME);

        while(true) {}
    }
}
