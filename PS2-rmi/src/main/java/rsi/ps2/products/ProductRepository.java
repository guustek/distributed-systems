package rsi.ps2.products;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

interface ProductRepository extends Remote {

    String REMOTE_NAME = "productRepository";

    Collection<Product> getAll() throws RemoteException;

    Product getByName(String name) throws RemoteException;
}
