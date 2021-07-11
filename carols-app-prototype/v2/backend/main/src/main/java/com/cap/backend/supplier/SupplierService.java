package com.cap.backend.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudService;
import com.cap.backend.model.datastore.SupplierEntity;
import com.cap.backend.model.hydrated.Supplier;

@Component
public class SupplierService extends CrudService<Supplier, SupplierEntity> {

	@Autowired
	public SupplierService(SupplierManager supplierManager) {
		super(supplierManager);
	}
}
