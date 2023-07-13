package com.example.crudintegr.service;

import com.example.crudintegr.dto.ProductDto;
import com.example.crudintegr.dto.ProductFullDto;
import com.example.crudintegr.entity.Product;
import com.example.crudintegr.entity.ProductType;
import com.example.crudintegr.mapper.ProductMapper;
import com.example.crudintegr.repository.ProductRepository;
import com.example.crudintegr.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeRepository productTypeRepository;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productTypeRepository = productTypeRepository;
    }

    public ProductFullDto create(ProductDto dto) {
        if (dto == null) throw new IllegalArgumentException("DTO should not be null");
        if (dto.getProductType().getId() == null && dto.getProductType().getName() == null) {
            throw new IllegalArgumentException("Product type ID or its name are required");
        }
        ProductType productType = getProductType(dto.getProductType().getId(), dto.getProductType().getName());

        Product product = productMapper.toEntity(dto);
        product.setProductType(productType);

        Product saved = productRepository.save(product);
        return productMapper.toFullDto(saved);
    }

    private ProductType getProductType(Long id, String name) {
        ProductType productType;
        if (id != null) {
            productType = productTypeRepository.findById(id).orElseThrow();
        } else {
            productType = productTypeRepository.findByNameIgnoreCase(name).orElseThrow();
        }
        return productType;
    }

    public ProductFullDto get(Long id) {
        if (id == null) throw new IllegalArgumentException("ID is required");
        Product product = productRepository.findById(id).orElseThrow();
        return productMapper.toFullDto(product);
    }

    public ProductFullDto update(ProductFullDto dto) {
        if (dto == null) throw new IllegalArgumentException("DTO should not be null");
        Product product = productRepository.findById(dto.getId()).orElseThrow();
        ProductType productType = getProductType(dto.getProductType().getId(), dto.getProductType().getName());
        product.setProductType(productType);
        Product update = productMapper.partialUpdate(dto, product);
        Product saved = productRepository.save(update);
        return productMapper.toFullDto(saved);
    }

    public boolean delete(Long id) {
        if (id == null) throw new IllegalArgumentException("ID is required");
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return true;
    }
}
