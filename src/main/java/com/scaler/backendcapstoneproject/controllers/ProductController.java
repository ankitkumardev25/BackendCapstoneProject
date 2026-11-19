package com.scaler.backendcapstoneproject.controllers;

import com.scaler.backendcapstoneproject.dtos.ProductDto;
import com.scaler.backendcapstoneproject.exceptions.ProductNotFoundException;
import com.scaler.backendcapstoneproject.mappers.ProductMapper;
import com.scaler.backendcapstoneproject.models.Product;
import com.scaler.backendcapstoneproject.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        validateId(id);
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return ProductMapper.toDto(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto request) {
        Product created = productService.createProduct(ProductMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto request) {
        validateId(id);
        Product updated = productService.replaceProduct(id, ProductMapper.toModel(request));
        if (updated == null) {
            throw new ProductNotFoundException(id);
        }
        return ProductMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        validateId(id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();   // 204
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product id must be a positive number");
        }
    }
}