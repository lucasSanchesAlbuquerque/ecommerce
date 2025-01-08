package com.lucassa.ecomerce.services;

import com.lucassa.ecomerce.dto.ProductDto;
import com.lucassa.ecomerce.entities.Product;
import com.lucassa.ecomerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findBiId(Long id){
        Optional<Product> product = productRepository.findById(id);
        return  new ProductDto(product.get());
        //return modelMapper.map(product,ProductDto.class);
    }

    public Page<ProductDto> findAll(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(x -> new ProductDto(x));
    }

    @Transactional
    public ProductDto insert(ProductDto dto){

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product = productRepository.save(product);

        return new ProductDto(product);
    }




}
