package com.naseer.productmanagement.serviceImpl;

import com.naseer.productmanagement.dto.ProductDto;
import com.naseer.productmanagement.entity.Product;
import com.naseer.productmanagement.repository.ProductRepository;
import com.naseer.productmanagement.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(productDto.getName());
        existing.setCategory(productDto.getCategory());
        existing.setPrice(productDto.getPrice());
        existing.setQuantity(productDto.getQuantity());

        productRepo.save(existing);

        return modelMapper.map(existing, ProductDto.class);
    }


    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);

    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepo.findById(id).orElse(null);
        ProductDto savedProduct = modelMapper.map(product, ProductDto.class);
        return savedProduct;
    }

    @Override
    public Page<ProductDto> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> all = productRepo.findAll(pageable);
        Page<ProductDto> dtoPage = all.map(product -> modelMapper.map(product, ProductDto.class));
        return dtoPage;
    }
}
