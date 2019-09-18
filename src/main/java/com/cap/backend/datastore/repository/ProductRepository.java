package com.cap.backend.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.backend.datastore.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
