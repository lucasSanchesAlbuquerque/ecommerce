package com.lucassa.ecomerce.repositories;

import com.lucassa.ecomerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT obj FROM Product " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    public Page<Product> searchByName(String name, Pageable pageable);
}
