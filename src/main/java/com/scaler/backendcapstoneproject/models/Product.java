package com.scaler.backendcapstoneproject.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product extends BaseModel {

    private String imageUrl;

    private String description;

    private BigDecimal price;

    private Category category;
}
