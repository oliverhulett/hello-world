package com.cap.backend.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudManager;
import com.cap.backend.model.datastore.SupplierEntity;
import com.cap.backend.model.hydrated.ImmutableSupplier;
import com.cap.backend.model.hydrated.Supplier;

@Component
public class SupplierManager extends CrudManager<Supplier, SupplierEntity> {

	@Autowired
	public SupplierManager(SupplierRepository repository) {
		super(repository);
	}

	@Override
	protected Supplier convert(SupplierEntity entity) {
		return ImmutableSupplier.builder()
		.id(entity.getId())
		.name(entity.getName())
		.description(entity.getDescription())
		.thumbnailUrl(entity.getThumbnailUrl())
		.fullImageUrl(entity.getFullImageUrl())
		.build();
	}
}
