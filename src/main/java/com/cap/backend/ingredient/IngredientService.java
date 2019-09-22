package com.cap.backend.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.datastore.entity.Ingredient;

@Component
public class IngredientService {

	private final IngredientManager ingredientManager;

	@Autowired
	public IngredientService(IngredientManager ingredientManager) {
		this.ingredientManager = ingredientManager;
	}

	public List<Ingredient> createOrUpdate(List<Ingredient> ingredients) {
		// TODO: permissions
		return ingredientManager.createOrUpdate(ingredients);
	}

	public List<Ingredient> get(List<Long> ingredientIds) {
		// TODO: permissions
		return ingredientManager.get(ingredientIds);
	}

	public void delete(List<Long> ingredientIds) {
		// TODO: permissions
		ingredientManager.delete(ingredientIds);
	}
}
