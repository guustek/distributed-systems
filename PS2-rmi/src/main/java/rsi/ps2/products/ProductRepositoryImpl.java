package rsi.ps2.products;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Collection;

class ProductRepositoryImpl extends UnicastRemoteObject implements ProductRepository {

    private static final Collection<Product> records = Arrays.asList(
            new Product("Name1", "Description1"),
            new Product("Name2", "Description2"),
            new Product("Name3", "Description3")
    );

    protected ProductRepositoryImpl() throws RemoteException {
        super();
    }

    @Override
    public Collection<Product> getAll() {
        return records;
    }

    @Override
    public Product getByName(String name) {
        return records.stream()
                .filter(product -> product.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
