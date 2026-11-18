package com.scaler.backendcapstoneproject.services;

import com.scaler.backendcapstoneproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product replaceProduct(Long id, Product product);
    void deleteProduct(Long id);
}
