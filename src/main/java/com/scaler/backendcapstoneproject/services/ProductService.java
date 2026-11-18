package com.scaler.backendcapstoneproject.services;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product replaceProduct(Long id, Product product);
    void deleteProduct(Long id);
}
