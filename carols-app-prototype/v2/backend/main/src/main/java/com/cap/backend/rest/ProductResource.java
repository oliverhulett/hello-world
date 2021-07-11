package com.cap.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.common.CrudResource;
import com.cap.backend.model.datastore.ProductEntity;
import com.cap.backend.model.hydrated.Product;
import com.cap.backend.product.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductResource extends CrudResource<Product, ProductEntity> {

	@Autowired
	public ProductResource(ProductService productService) {
		super(productService, "product");
	}
}
