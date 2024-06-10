package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.MyFilter;
import org.example.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private final Map<Long, Product> productMap = new HashMap<>();
    
    public ProductService() {
        productMap.put(1L, new Product(1L, "TV", 3000));
        productMap.put(2L, new Product(2L, "Phone", 2500));
        productMap.put(3L, new Product(3L, "PC", 4000));
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
    
    public List<Product> getAllProducts(MyFilter myFilter) {
        return new ArrayList<>(productMap.values()).stream()
                .filter(product -> product.getPrice() > myFilter.getPrice())
                .toList();
    }
}
