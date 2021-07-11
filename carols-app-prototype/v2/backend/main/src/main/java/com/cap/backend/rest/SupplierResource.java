package com.cap.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.common.CrudResource;
import com.cap.backend.model.datastore.SupplierEntity;
import com.cap.backend.model.hydrated.Supplier;
import com.cap.backend.supplier.SupplierService;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierResource extends CrudResource<Supplier, SupplierEntity> {

	@Autowired
	public SupplierResource(SupplierService supplierService) {
		super(supplierService, "supplier");
	}
}
