package com.cap.backend.model.hydrated;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableIngredient.class)
@JsonDeserialize(builder = ImmutableIngredient.Builder.class)
public interface Ingredient {

	String getName();
	String getDescription();
	String getThumbnailUrl();
	String getFullImageUrl();
}
