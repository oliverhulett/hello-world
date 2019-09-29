package com.cap.backend.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudService;
import com.cap.backend.datastore.entity.Ingredient;

@Component
public class IngredientService extends CrudService<Ingredient> {

	@Autowired
	public IngredientService(IngredientManager ingredientManager) {
		super(ingredientManager);
	}
}
