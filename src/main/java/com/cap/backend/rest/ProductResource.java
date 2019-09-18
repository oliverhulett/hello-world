package com.cap.backend.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.datastore.entity.Product;
import com.cap.backend.product.ProductManager;
import com.cap.backend.rest.exception.ResourceNotFoundException;

@RestController
@RequestMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

	private final ProductManager productManager;

	@Autowired
	public ProductResource(ProductManager productManager) {
		this.productManager = productManager;
	}

	@GetMapping("/product/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		try {
			return productManager.getById(productId);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(
					"Product not found with ID: " + productId, e);
		}
	}
}
