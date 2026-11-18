package com.scaler.backendcapstoneproject.controllers;

import com.scaler.backendcapstoneproject.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product dummyProduct = new Product();
        dummyProduct.setTitle("Dummy Product");
        dummyProduct.setPrice(19.99);
        dummyProduct.setDescription("This is a dummy product for demonstration purposes.");
        // Add the dummy product to a list and return it
        return List.of(dummyProduct);
        // return dummyProduct;
    }
}
