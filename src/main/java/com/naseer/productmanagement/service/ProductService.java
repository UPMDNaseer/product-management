package com.naseer.productmanagement.service;

import com.naseer.productmanagement.dto.ProductDto;
import com.naseer.productmanagement.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDto saveProduct(ProductDto product);
    ProductDto updateProduct(Long id, ProductDto product);
    void deleteProduct(Long id);
    ProductDto getProductById(Long id);
    Page<ProductDto> getAllProducts(int page, int size, String sortBy);
}
