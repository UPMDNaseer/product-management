package com.naseer.productmanagement.service;

import com.naseer.productmanagement.entity.Product;
import com.naseer.productmanagement.repository.ProductRepository;
import com.naseer.productmanagement.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testSaveProduct_success() {
        // Given
        Product inputProduct = new Product(null, "iPhone", "Electronics", 999.99, 10);
        Product savedProduct = new Product(1L, "iPhone", "Electronics", 999.99, 10);

        // When
        Mockito.when(productRepo.save(Mockito.any(Product.class))).thenReturn(savedProduct);


        // Then
        Product result = productService.saveProduct(inputProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("iPhone", result.getName());
        Assertions.assertEquals("Electronics", result.getCategory());
        Assertions.assertEquals(999.99, result.getPrice());
        Assertions.assertEquals(10, result.getQuantity());

        Mockito.verify(productRepo, Mockito.times(1)).save(inputProduct);
    }

    @Test
    void testSaveProduct_nullProduct_shouldThrowException() {
        Mockito.when(productRepo.save(null)).thenThrow(new IllegalArgumentException("Product cannot be null"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.saveProduct(null);
        });

        Mockito.verify(productRepo, Mockito.times(1)).save(null);
    }
}

