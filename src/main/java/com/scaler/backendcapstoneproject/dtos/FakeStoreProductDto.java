package com.scaler.backendcapstoneproject.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;

    // Getters and setters
}
