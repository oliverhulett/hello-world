package com.cap.backend.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.datastore.entity.Supplier;

@Component
public class SupplierService {

	private final SupplierManager supplierManager;

	@Autowired
	public SupplierService(SupplierManager supplierManager) {
		this.supplierManager = supplierManager;
	}

	public List<Supplier> createOrUpdate(List<Supplier> suppliers) {
		// TODO: permissions
		return supplierManager.createOrUpdate(suppliers);
	}

	public List<Supplier> get(List<Long> supplierIds) {
		// TODO: permissions
		return supplierManager.get(supplierIds);
	}

	public void delete(List<Long> supplierIds) {
		// TODO: permissions
		supplierManager.delete(supplierIds);
	}
}
