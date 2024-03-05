package rsi.ps2.products;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.List;

class ProductRepositoryImpl extends UnicastRemoteObject implements ProductRepository {

    private static final Collection<Product> records = List.of(
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
                .filter(rec -> rec.name().equals(name))
                .findAny()
                .orElse(null);
    }
}
