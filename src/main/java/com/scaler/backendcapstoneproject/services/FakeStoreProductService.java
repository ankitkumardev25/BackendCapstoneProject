package com.scaler.backendcapstoneproject.services;

import com.scaler.backendcapstoneproject.clients.FakeStoreClient;
import com.scaler.backendcapstoneproject.dtos.FakeStoreProductDto;
import com.scaler.backendcapstoneproject.models.Category;
import com.scaler.backendcapstoneproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    // Constructor injection: no @Autowired needed with a single constructor
    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto dto = fakeStoreClient.getProductById(id);
        return dto == null ? null : toProduct(dto);   // controller turns null into 404
    }

    @Override
    public List<Product> getAllProducts() {
        return fakeStoreClient.getAllProducts().stream()
                .map(this::toProduct)
                .toList();
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto response = fakeStoreClient.createProduct(toFakeStoreDto(product));
        return toProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto response = fakeStoreClient.replaceProduct(id, toFakeStoreDto(product));
        return response == null ? null : toProduct(response);
    }

    @Override
    public void deleteProduct(Long id) {
        fakeStoreClient.deleteProduct(id);
    }

    // ---- mapping between FakeStore's shape and our model ----

    private Product toProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getTitle());          // FakeStore calls it "title"
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImage());      // FakeStore calls it "image"
        if (dto.getCategory() != null) {
            Category category = new Category();
            category.setName(dto.getCategory());  // FakeStore category is just a string
            product.setCategory(category);
        }
        return product;
    }

    private FakeStoreProductDto toFakeStoreDto(Product product) {
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImageUrl());
        if (product.getCategory() != null) {
            dto.setCategory(product.getCategory().getName());
        }
        return dto;
    }
}