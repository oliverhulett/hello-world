package com.cap.backend.product;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudManager;
import com.cap.backend.model.datastore.ProductEntity;
import com.cap.backend.model.hydrated.Product;

@Component
public class ProductManager extends CrudManager<Product, ProductEntity> {

	@Autowired
	public ProductManager(ProductRepository repository) {
		super(repository);
	}

	@Override
	protected Product convert(ProductEntity entity) {
		throw new NotImplementedException("Product converter");
	}
}
