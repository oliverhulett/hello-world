package com.cap.backend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudService;
import com.cap.backend.model.datastore.ProductEntity;
import com.cap.backend.model.hydrated.Product;

@Component
public class ProductService extends CrudService<Product, ProductEntity> {

	@Autowired
	public ProductService(ProductManager productManager) {
		super(productManager);
	}
}
