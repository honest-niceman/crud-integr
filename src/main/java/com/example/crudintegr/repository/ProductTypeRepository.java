package com.example.crudintegr.repository;

import com.example.crudintegr.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    Optional<ProductType> findByNameIgnoreCase(String name);
}