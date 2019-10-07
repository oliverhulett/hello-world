package com.cap.backend.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.common.CrudManager;
import com.cap.backend.datastore.entity.Ingredient;
import com.cap.backend.datastore.repository.IngredientRepository;

@Component
public class IngredientManager extends CrudManager<Ingredient> {

	@Autowired
	public IngredientManager(IngredientRepository ingredientRepository) {
		super(ingredientRepository);
	}
}
