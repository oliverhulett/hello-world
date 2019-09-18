package com.cap.backend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.datastore.entity.Product;
import com.cap.backend.datastore.repository.ProductRepository;

@Component
public class ProductManager {

	private final ProductRepository productRepository;

	@Autowired
	public ProductManager(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product getById(long id) {
		return productRepository.getOne(id);
	}
}
