package com.cap.backend.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class CrudManager<HydratedType, EntityType> {

	private final MongoRepository<EntityType, String> repository;

	public CrudManager(MongoRepository<EntityType, String> repository) {
		this.repository = repository;
	}

	public List<HydratedType> createOrUpdate(List<EntityType> entities) {
		return convertList(repository.saveAll(entities));
	}

	public List<HydratedType> get(List<String> idList) {
		return convertList(repository.findAllById(idList));
	}

	public void delete(List<String> idList) {
		for (String id : idList) {
			repository.deleteById(id);
		}
	}

	public List<HydratedType> getPage(int pageNumber, int size) {
		return repository.findAll(PageRequest.of(pageNumber, size)).stream().map(this::convert)
				.collect(Collectors.toList());
	}

	protected abstract HydratedType convert(EntityType entity);

	protected List<HydratedType> convertList(Iterable<EntityType> entityList) {
		List<HydratedType> hydratedList = new ArrayList<>();
		for (EntityType entity : entityList) {
			hydratedList.add(convert(entity));
		}
		return hydratedList;
	}
}
