package com.lucassa.ecomerce.repositories;

import com.lucassa.ecomerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
