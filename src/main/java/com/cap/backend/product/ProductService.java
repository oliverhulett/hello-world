package com.cap.backend.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.datastore.entity.Product;

@Component
public class ProductService {

	private final ProductManager productManager;

	@Autowired
	public ProductService(ProductManager productManager) {
		this.productManager = productManager;
	}

	public List<Product> createOrUpdate(List<Product> products) {
		// TODO: permissions
		return productManager.createOrUpdate(products);
	}

	public List<Product> get(List<Long> productIds) {
		// TODO: permissions
		return productManager.get(productIds);
	}

	public void delete(List<Long> productIds) {
		// TODO: permissions
		productManager.delete(productIds);
	}
}
