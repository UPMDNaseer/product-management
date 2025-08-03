package com.naseer.productmanagement.controller;

import com.naseer.productmanagement.entity.Product;
import com.naseer.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/bulk")
    public List<Product> createBulk(@RequestBody List<Product> products) {
        return products.stream()
                .map(productService::saveProduct)
                .collect(Collectors.toList());
    }


    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<Product> getall(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "name") String sortBy){
        return productService.getAllProducts(page, size, sortBy);
    }

}
