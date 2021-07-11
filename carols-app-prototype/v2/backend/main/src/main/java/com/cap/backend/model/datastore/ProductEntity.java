package com.cap.backend.model.datastore;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

@Document
@Value.Immutable
@JsonSerialize(as = ImmutableProductEntity.class)
@JsonDeserialize(builder = ImmutableProductEntity.Builder.class)
public interface ProductEntity {

	@Id
	@Nullable
	String getId();

	String getName();
	String getSupplierId();

	String getDescription();
	String getThumbnailUrl();
	String getFullImageUrl();

	List<IngredientEntity> getIngredients();
}
