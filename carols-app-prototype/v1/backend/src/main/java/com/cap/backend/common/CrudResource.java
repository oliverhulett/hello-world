package com.cap.backend.common;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestParam;

import com.cap.backend.common.log.ContextLogger;
import com.cap.backend.common.log.ContextLoggerFactory;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CrudResource<EntityType> {

	private static ContextLogger log = ContextLoggerFactory.getLogger(CrudResource.class);

	private final CrudService<EntityType> service;
	private final String entityName;

	public CrudResource(CrudService<EntityType> service, String entityName) {
		this.service = service;
		this.entityName = entityName;
	}

	// TODO: Add end-point to get hydrated versions of data classes...
	// TODO: Manage response codes. 200/202 for full success. 207 for
	// multi-status (some individual elements failed). 4xx/5xx for full failure.
	// TODO: Add access logs to log context via filter, use out-going filter to
	// actually log the line, remove actual log lines here.

	@GET
	@Path("list")
	public List<EntityType> getAll(@QueryParam("start") Long start, @QueryParam("size") Long size) {
		try {
			if (start == null) {
				start = 0L;
			}
			if (size == null) {
				size = 1000L;
			}
			log.withContext("rest." + entityName + ".list.start", start);
			log.withContext("rest." + entityName + ".list.size", size);
			List<EntityType> resp = service.getPage(start, size);
			log.withContext("rest." + entityName + ".list.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".list.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@POST
	@Path("/create-or-update")
	public List<EntityType> createOrUpdate(@RequestParam List<EntityType> entities) {
		try {
			log.withContext("rest." + entityName + ".create-or-update.request", entities);
			List<EntityType> resp = service.createOrUpdate(entities);
			log.withContext("rest." + entityName + ".create-or-update.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".create-or-update.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@POST
	@Path("get")
	public List<EntityType> getById(@RequestParam List<Long> idList) {
		try {
			log.withContext("rest." + entityName + ".get.request", idList);
			List<EntityType> resp = service.get(idList);
			log.withContext("rest." + entityName + ".get.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest." + entityName + ".get.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@POST
	@Path("delete")
	public void deleteById(@RequestParam List<Long> idList) {
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
