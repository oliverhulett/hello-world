package com.cap.backend.datastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.backend.datastore.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
