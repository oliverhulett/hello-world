package com.cap.backend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudManager;
import com.cap.backend.datastore.entity.Product;
import com.cap.backend.datastore.repository.ProductRepository;

@Component
public class ProductManager extends CrudManager<Product> {

	@Autowired
	public ProductManager(ProductRepository productRepository) {
		super(productRepository);
	}
}
