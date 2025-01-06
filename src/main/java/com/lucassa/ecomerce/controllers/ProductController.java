package com.lucassa.ecomerce.controllers;

import com.lucassa.ecomerce.dto.ProductDto;
import com.lucassa.ecomerce.entities.Product;
import com.lucassa.ecomerce.repositories.ProductRepository;
import com.lucassa.ecomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping()
    public ProductDto findById(){
        return productService.findBiId(1L);
    }

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id){
        return productService.findBiId(id);
    }
}
