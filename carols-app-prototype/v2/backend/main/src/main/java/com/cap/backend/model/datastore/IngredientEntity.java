package com.cap.backend.model.datastore;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableIngredientEntity.class)
@JsonDeserialize(builder = ImmutableIngredientEntity.Builder.class)
public interface IngredientEntity {

	String getName();
	String getDescription();
	String getThumbnailUrl();
	String getFullImageUrl();
}
