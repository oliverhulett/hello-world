package com.cap.backend.rest;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.common.CrudResource;
import com.cap.backend.datastore.entity.Product;
import com.cap.backend.product.ProductService;

@Path("product")
@RestController
public class ProductResource extends CrudResource<Product> {

	@Autowired
	public ProductResource(ProductService productService) {
		super(productService, "product");
	}
}
