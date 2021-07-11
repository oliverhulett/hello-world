package com.cap.backend.common;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cap.backend.common.log.ContextLogger;
import com.cap.backend.common.log.ContextLoggerFactory;

@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class CrudResource<HydratedType, EntityType> {

	private final ContextLogger log = ContextLoggerFactory.getLogger(this.getClass());

	private final CrudService<HydratedType, EntityType> service;
	private final String entityName;

	public CrudResource(CrudService<HydratedType, EntityType> service, String entityName) {
		this.service = service;
		this.entityName = entityName;
	}

	// TODO: Add end-point to get hydrated versions of data classes...
	// TODO: Manage response codes. 200/202 for full success. 207 for
	// multi-status (some individual elements failed). 4xx/5xx for full failure.
	// TODO: Add access logs to log context via filter, use out-going filter to
	// actually log the line, remove actual log lines here.

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<HydratedType> getAll(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(name = "size", required = false) Integer size) {
		log.resetContext();
		try {
			if (pageNumber == null) {
				pageNumber = 0;
			}
			if (size == null) {
				size = 1000;
			}
			log.withContext("rest." + entityName + ".list.pageNumber", pageNumber);
			log.withContext("rest." + entityName + ".list.size", size);
			List<HydratedType> resp = service.getPage(pageNumber, size);
			log.withContext("rest." + entityName + ".list.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".list.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@RequestMapping(path = "/create-or-update", method = RequestMethod.POST)
	public List<HydratedType> createOrUpdate(@RequestBody List<EntityType> entities) {
		log.resetContext();
		try {
			log.withContext("rest." + entityName + ".create-or-update.request", entities);
			List<HydratedType> resp = service.createOrUpdate(entities);
			log.withContext("rest." + entityName + ".create-or-update.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".create-or-update.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public List<HydratedType> getById(@RequestBody List<String> idList) {
		log.resetContext();
		try {
			log.withContext("rest." + entityName + ".get.request", idList);
			List<HydratedType> resp = service.get(idList);
			log.withContext("rest." + entityName + ".get.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".get.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestBody List<String> idList) {
		log.resetContext();
		try {
			log.withContext("rest." + entityName + ".delete.request", idList);
			service.delete(idList);
			log.info("Handled REST request");
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".delete.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}
}
