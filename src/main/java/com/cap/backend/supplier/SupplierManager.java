package com.cap.backend.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.datastore.entity.Supplier;
import com.cap.backend.datastore.repository.SupplierRepository;

@Component
public class SupplierManager {

	private final SupplierRepository supplierRepository;

	@Autowired
	public SupplierManager(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	public List<Supplier> createOrUpdate(List<Supplier> suppliers) {
		return supplierRepository.saveAll(suppliers);
	}

	public List<Supplier> get(List<Long> supplierIds) {
		return supplierRepository.findAllById(supplierIds);
	}

	public void delete(List<Long> supplierIds) {
		for (Long id : supplierIds) {
			supplierRepository.deleteById(id);
		}
	}
}
