package com.cap.backend.product;

import java.util.List;

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

	public List<Product> createOrUpdate(List<Product> products) {
		return productRepository.saveAll(products);
	}

	public List<Product> get(List<Long> productIds) {
		return productRepository.findAllById(productIds);
	}

	public void delete(List<Long> productIds) {
		for (Long id : productIds) {
			productRepository.deleteById(id);
		}
	}
}
