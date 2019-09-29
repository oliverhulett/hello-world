package com.cap.backend.rest;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.common.CrudResource;
import com.cap.backend.datastore.entity.Supplier;
import com.cap.backend.supplier.SupplierService;

@Path("supplier")
@RestController
public class SupplierResource extends CrudResource<Supplier> {

	@Autowired
	public SupplierResource(SupplierService supplierService) {
		super(supplierService, "supplier");
	}
}
