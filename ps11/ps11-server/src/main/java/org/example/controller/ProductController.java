package org.example.controller;

import java.util.List;

import org.example.MyFilter;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    
    
    // ps11 - zad4 - serwis przyjmujacy obiekt jako parametr i zwracajacy liste obiektow
    @PostMapping(value = "/filtered-products")
    public List<Product> getProducts(@RequestBody MyFilter myFilter) {
        return productService.getAllProducts(myFilter);
    }
}
