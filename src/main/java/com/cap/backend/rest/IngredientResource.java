package com.cap.backend.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.cap.backend.common.log.ContextLogger;
import com.cap.backend.common.log.ContextLoggerFactory;
import com.cap.backend.datastore.entity.Ingredient;
import com.cap.backend.ingredient.IngredientService;

@Path("ingredient")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class IngredientResource {

	private static ContextLogger log = ContextLoggerFactory.getLogger(IngredientResource.class);

	private final IngredientService ingredientService;

	@Autowired
	public IngredientResource(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	// TODO: Add end-point to get hydrated versions of data classes...
	// TODO: Manage response codes. 200/202 for full success. 207 for
	// multi-status (some individual elements failed). 4xx/5xx for full failure.
	// TODO: Add access logs to log context via filter, use out-going filter to
	// actually log the line, remove actual log lines here.

	@POST
	@Path("/create-or-update")
	public List<Ingredient> createOrUpdateIngredients(@RequestParam List<Ingredient> ingredients) {
		try {
			log.withContext("rest.ingredient.create-or-update.request", ingredients);
			List<Ingredient> resp = ingredientService.createOrUpdate(ingredients);
			log.withContext("rest.ingredient.create-or-update.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest.ingredient.create-or-update.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@POST
	@Path("get")
	public List<Ingredient> getIngredientsById(@RequestParam List<Long> ingredientIds) {
		try {
			log.withContext("rest.ingredient.get.request", ingredientIds);
			List<Ingredient> resp = ingredientService.get(ingredientIds);
			log.withContext("rest.ingredient.get.response", resp);
			log.info("Handled REST request");
			return resp;
		} catch (Exception e) {
			log.withContext("rest.ingredient.get.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}

	@POST
	@Path("delete")
	public void deleteIngredientsById(@RequestParam List<Long> ingredientIds) {
		try {
			log.withContext("rest.ingredient.delete.request", ingredientIds);
			ingredientService.delete(ingredientIds);
			log.info("Handled REST request");
		} catch (Exception e) {
			log.withContext("rest.ingredient.delete.exception", e);
			log.error("Exception while handling REST request");
			throw e;
		}
	}
}
