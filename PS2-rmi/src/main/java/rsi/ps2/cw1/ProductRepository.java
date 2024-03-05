package rsi.ps2.cw1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ProductRepository extends Remote {

    String REMOTE_NAME = "productRepository";

    Collection<Product> getAll() throws RemoteException;

    Product getByName(String name) throws RemoteException;
}
