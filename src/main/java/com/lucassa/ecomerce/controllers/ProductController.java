package com.lucassa.ecomerce.controllers;

import com.lucassa.ecomerce.dto.ProductDto;
import com.lucassa.ecomerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable){
        Page<ProductDto> dto = productService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto>  findById(@PathVariable Long id){
        ProductDto dto = productService.findBiId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto dto){
        dto =  productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }
}
