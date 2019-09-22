package com.cap.backend.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cap.backend.datastore.entity.Ingredient;
import com.cap.backend.datastore.repository.IngredientRepository;

@Component
public class IngredientManager {

	private final IngredientRepository ingredientRepository;

	@Autowired
	public IngredientManager(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<Ingredient> createOrUpdate(List<Ingredient> ingredients) {
		return ingredientRepository.saveAll(ingredients);
	}

	public List<Ingredient> get(List<Long> ingredientIds) {
		return ingredientRepository.findAllById(ingredientIds);
	}

	public void delete(List<Long> ingredientIds) {
		for (Long id : ingredientIds) {
			ingredientRepository.deleteById(id);
		}
	}
}
