package com.lucassa.ecomerce.services;

import com.lucassa.ecomerce.dto.ProductDto;
import com.lucassa.ecomerce.entities.Product;
import com.lucassa.ecomerce.repositories.ProductRepository;
import com.lucassa.ecomerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDto findBiId(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return  new ProductDto(product);
        //return modelMapper.map(product,ProductDto.class);
    }

    public Page<ProductDto> findAll(String name, Pageable pageable){
        Page<Product> products = productRepository.searchByName(name,pageable);
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

    @Transactional
    public ProductDto update(Long id, ProductDto dto){
       try {
           Product product = productRepository.getReferenceById(id);
           product.setName(dto.getName());
           product.setDescription(dto.getDescription());
           product.setPrice(dto.getPrice());
           product.setImgUrl(dto.getImgUrl());
           product = productRepository.save(product);

           return new ProductDto(product);

       }catch (EntityNotFoundException e){
          throw new ResourceNotFoundException("Recuso não encontrado");
       }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }


}
