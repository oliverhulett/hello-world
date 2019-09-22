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
import com.cap.backend.datastore.entity.Supplier;
import com.cap.backend.supplier.SupplierService;

@RestController
@RequestMapping(path = "/supplier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierResource {

	private static ContextLogger log = ContextLoggerFactory.getLogger(SupplierResource.class);

	private final SupplierService supplierService;

	@Autowired
	public SupplierResource(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	// TODO: Add end-point to get hydrated versions of data classes...
	// TODO: Manage response codes. 200/202 for full success. 207 for
	// multi-status (some individual elements failed). 4xx/5xx for full failure.
	// TODO: Add access logs to log context via filter, use out-going filter to
	// actually log the line, remove actual log lines here.

	@PostMapping("/create-or-update")
	public List<Supplier> createOrUpdateSuppliers(@RequestParam List<Supplier> suppliers) {
		try {
			log.withContext("rest.supplier.create-or-update.request", suppliers);
			List<Supplier> resp = supplierService.createOrUpdate(suppliers);
			log.withContext("rest.supplier.create-or-update.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest.supplier.create-or-update.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@PostMapping("get")
	public List<Supplier> getSuppliersById(@RequestParam List<Long> supplierIds) {
		try {
			log.withContext("rest.supplier.get.request", supplierIds);
			List<Supplier> resp = supplierService.get(supplierIds);
			log.withContext("rest.supplier.get.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest.supplier.get.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@PostMapping("delete")
	public void deleteSuppliersById(@RequestParam List<Long> supplierIds) {
		try {
			log.withContext("rest.supplier.delete.request", supplierIds);
			supplierService.delete(supplierIds);
			log.info("Handled REST request");
		} catch (Exception e) {
			log.withContext("rest.supplier.delete.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}
}
