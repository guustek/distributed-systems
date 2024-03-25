package rsi.ps4.server.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ProductRepositoryImpl implements ProductRepository {

    private static final Collection<Product> products = new ArrayList<>(Arrays.asList(
            new Product("Name1", "Description1", 100),
            new Product("Name2", "Description2", 200),
            new Product("Name3", "Description3", 300)
    ));

    @Override
    public Collection<Product> getAll() {
        return products;
    }

    @Override
    public Product getByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }
}
