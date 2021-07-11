package com.cap.backend.model.hydrated;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableProduct.class)
@JsonDeserialize(builder = ImmutableProduct.Builder.class)
public interface Product {

	String getId();

	String getName();
	Supplier getSupplier();

	String getDescription();
	String getThumbnailUrl();
	String getFullImageUrl();

	List<Ingredient> getIngredients();
}
