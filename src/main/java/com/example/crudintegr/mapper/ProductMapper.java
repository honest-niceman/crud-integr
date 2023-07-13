package com.example.crudintegr.mapper;

import com.example.crudintegr.entity.Product;
import com.example.crudintegr.dto.ProductDto;
import com.example.crudintegr.dto.ProductFullDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDto productDto, @MappingTarget Product product);

    Product toEntity(ProductFullDto productFullDto);

    ProductFullDto toFullDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductFullDto productFullDto, @MappingTarget Product product);

}