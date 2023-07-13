package com.example.crudintegr.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.crudintegr.entity.Product}
 */
@Value
public class ProductDto implements Serializable {
    String name;
    ProductTypeDto productType;

    /**
     * DTO for {@link com.example.crudintegr.entity.ProductType}
     */
    @Value
    public static class ProductTypeDto implements Serializable {
        Long id;
        String name;
    }
}