package com.scaler.backendcapstoneproject.mappers;

import com.scaler.backendcapstoneproject.dtos.CategoryDto;
import com.scaler.backendcapstoneproject.dtos.ProductDto;
import com.scaler.backendcapstoneproject.models.Category;
import com.scaler.backendcapstoneproject.models.Product;

public final class ProductMapper {

    private ProductMapper() {}   // utility class, no instances

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            dto.setCategory(categoryDto);
        }
        return dto;
    }

    public static Product toModel(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        if (dto.getCategory() != null) {
            Category category = new Category();
            category.setId(dto.getCategory().getId());
            category.setName(dto.getCategory().getName());
            category.setDescription(dto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }
}