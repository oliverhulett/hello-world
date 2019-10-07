package com.cap.backend.common;

import java.util.List;

public abstract class CrudService<EntityType> {

	private final CrudManager<EntityType> manager;

	public CrudService(CrudManager<EntityType> manager) {
		this.manager = manager;
	}

	public List<EntityType> createOrUpdate(List<EntityType> entities) {
		// TODO: permissions
		return manager.createOrUpdate(entities);
	}

	public List<EntityType> get(List<Long> idList) {
		// TODO: permissions
		return manager.get(idList);
	}

	public void delete(List<Long> idList) {
		// TODO: permissions
		manager.delete(idList);
	}

	public List<EntityType> getPage(long start, long size) {
		// TODO: permissions
		return manager.getPage(start, size);
	}
}