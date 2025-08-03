package com.naseer.productmanagement.controller;

import com.naseer.productmanagement.dto.ProductDto;
import com.naseer.productmanagement.service.ProductService;
import jakarta.validation.Valid;
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

    @PostMapping("/create")
    public ProductDto createOne(@Valid @RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }


    @PostMapping("/bulk")
    public List<ProductDto> createBulk(@Valid @RequestBody List<ProductDto> products) {
        return products.stream()
                .map(productService::saveProduct)
                .collect(Collectors.toList());
    }


    @PutMapping("/{id}")
    public ProductDto update(@Valid @PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<ProductDto> getall(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "name") String sortBy){
        return productService.getAllProducts(page, size, sortBy);
    }

}
