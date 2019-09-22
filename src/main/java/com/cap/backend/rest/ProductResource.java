package com.cap.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.common.log.ContextLogger;
import com.cap.backend.common.log.ContextLoggerFactory;
import com.cap.backend.datastore.entity.Product;
import com.cap.backend.product.ProductService;

@RestController
@RequestMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

	private static ContextLogger log = ContextLoggerFactory.getLogger(ProductResource.class);

	private final ProductService productService;

	@Autowired
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	// TODO: Add end-point to get hydrated versions of data classes...
	// TODO: Manage response codes. 200/202 for full success. 207 for
	// multi-status (some individual elements failed). 4xx/5xx for full failure.
	// TODO: Add access logs to log context via filter, use out-going filter to
	// actually log the line, remove actual log lines here.

	@PostMapping("/create-or-update")
	public List<Product> createOrUpdateProducts(@RequestParam List<Product> products) {
		try {
			log.withContext("rest.product.create-or-update.request", products);
			List<Product> resp = productService.createOrUpdate(products);
			log.withContext("rest.product.create-or-update.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest.product.create-or-update.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@PostMapping("get")
	public List<Product> getProductsById(@RequestParam List<Long> productIds) {
		try {
			log.withContext("rest.product.get.request", productIds);
			List<Product> resp = productService.get(productIds);
			log.withContext("rest.product.get.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest.product.get.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@PostMapping("delete")
	public void deleteProductsById(@RequestParam List<Long> productIds) {
		try {
			log.withContext("rest.product.delete.request", productIds);
			productService.delete(productIds);
			log.info("Handled REST request");
		} catch (Exception e) {
			log.withContext("rest.product.delete.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}
}
