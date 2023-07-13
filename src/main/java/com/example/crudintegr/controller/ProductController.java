package com.example.crudintegr.controller;

import com.example.crudintegr.dto.ProductDto;
import com.example.crudintegr.dto.ProductFullDto;
import com.example.crudintegr.service.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductFullDto create(@RequestBody ProductDto dto) {
        return productService.create(dto);
    }

    @GetMapping("/{id}")
    public ProductFullDto get(@PathVariable Long id) {
        return productService.get(id);
    }

    @PatchMapping
    public ProductFullDto update(@RequestBody ProductFullDto dto) {
        return productService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatusCode.valueOf(200));
    }
}
