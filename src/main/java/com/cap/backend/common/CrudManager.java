package com.cap.backend.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudManager<EntityType> {

	private final JpaRepository<EntityType, Long> repository;

	public CrudManager(JpaRepository<EntityType, Long> repository) {
		this.repository = repository;
	}

	public List<EntityType> createOrUpdate(List<EntityType> entities) {
		return repository.saveAll(entities);
	}

	public List<EntityType> get(List<Long> idList) {
		return repository.findAllById(idList);
	}

	public void delete(List<Long> idList) {
		for (Long id : idList) {
			repository.deleteById(id);
		}
	}

	public List<EntityType> getPage(long start, long size) {
		// TODO: Handle paging...
		return repository.findAll();
	}
}
