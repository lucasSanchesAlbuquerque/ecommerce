package com.lucassa.ecomerce.services;

import com.lucassa.ecomerce.dto.ProductDto;
import com.lucassa.ecomerce.entities.Product;
import com.lucassa.ecomerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findBiId(Long id){
        Optional<Product> product = productRepository.findById(id);
        return new ProductDto(product.get());
    }
}
