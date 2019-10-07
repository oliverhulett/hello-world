package com.cap.backend.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudService;
import com.cap.backend.datastore.entity.Supplier;

@Component
public class SupplierService extends CrudService<Supplier> {

	@Autowired
	public SupplierService(SupplierManager supplierManager) {
		super(supplierManager);
	}
}
