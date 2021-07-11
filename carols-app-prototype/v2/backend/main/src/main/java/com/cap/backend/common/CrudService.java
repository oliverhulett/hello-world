package com.cap.backend.common;

import java.util.List;

public abstract class CrudService<HydratedType, EntityType> {

	private final CrudManager<HydratedType, EntityType> manager;

	public CrudService(CrudManager<HydratedType, EntityType> manager) {
		this.manager = manager;
	}

	public List<HydratedType> createOrUpdate(List<EntityType> entities) {
		// TODO: permissions
		return manager.createOrUpdate(entities);
	}

	public List<HydratedType> get(List<String> idList) {
		// TODO: permissions
		return manager.get(idList);
	}

	public void delete(List<String> idList) {
		// TODO: permissions
		manager.delete(idList);
	}

	public List<HydratedType> getPage(int pageNumber, int size) {
		// TODO: permissions
		return manager.getPage(pageNumber, size);
	}
}