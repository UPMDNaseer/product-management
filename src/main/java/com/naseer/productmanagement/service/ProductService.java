package com.naseer.productmanagement.service;

import com.naseer.productmanagement.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    Page<Product> getAllProducts(int page, int size, String sortBy);
}
