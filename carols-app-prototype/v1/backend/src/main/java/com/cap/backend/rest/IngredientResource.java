package com.cap.backend.rest;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cap.backend.common.CrudResource;
import com.cap.backend.datastore.entity.Ingredient;
import com.cap.backend.ingredient.IngredientService;

@Path("ingredient")
@RestController
public class IngredientResource extends CrudResource<Ingredient> {

	@Autowired
	public IngredientResource(IngredientService ingredientService) {
		super(ingredientService, "ingredient");
	}
}
