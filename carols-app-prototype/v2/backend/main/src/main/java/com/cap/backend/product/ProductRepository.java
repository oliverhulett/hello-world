package com.cap.backend.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cap.backend.model.datastore.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

	// public List<ProductEntity> findByName(String name);
	// public List<ProductEntity> findFromSupplier(String supplierId);
}
