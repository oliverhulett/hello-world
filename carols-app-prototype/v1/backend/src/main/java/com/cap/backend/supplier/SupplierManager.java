package com.cap.backend.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudManager;
import com.cap.backend.datastore.entity.Supplier;
import com.cap.backend.datastore.repository.SupplierRepository;

@Component
public class SupplierManager extends CrudManager<Supplier> {

	@Autowired
	public SupplierManager(SupplierRepository supplierRepository) {
		super(supplierRepository);
	}
}
