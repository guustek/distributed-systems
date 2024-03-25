package rsi.ps4.server.products;

import java.util.Collection;

public interface ProductRepository {

    Collection<Product> getAll();

    Product getByName(String name);

    void add(Product product);
}
