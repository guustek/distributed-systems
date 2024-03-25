package rsi.ps4.server.services;

import javax.jws.WebService;

import java.util.Collection;

import rsi.ps4.server.products.Product;
import rsi.ps4.server.products.ProductRepository;
import rsi.ps4.server.products.ProductRepositoryImpl;

@WebService(endpointInterface = "rsi.ps4.server.services.PS4Service", serviceName = "PS4ServiceImpl")
public class PS4ServiceImpl implements PS4Service {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public String getHello(String name) {
        System.out.println("Received parameter: " + name);
        return "Hello from JAX-WS: " + name;
    }

    @Override
    public Collection<Product> getProducts() {
        return productRepository.getAll();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.getByName(name);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.add(product);
    }
}
