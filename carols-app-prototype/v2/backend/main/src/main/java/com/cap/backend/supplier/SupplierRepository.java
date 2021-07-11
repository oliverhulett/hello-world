package com.cap.backend.supplier;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cap.backend.model.datastore.SupplierEntity;

public interface SupplierRepository extends MongoRepository<SupplierEntity, String> {

	// public List<SupplierEntity> findByName(String name);
}
