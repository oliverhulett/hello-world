package com.cap.backend.model.datastore;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

@Document
@Value.Immutable
@JsonSerialize(as = ImmutableSupplierEntity.class)
@JsonDeserialize(builder = ImmutableSupplierEntity.Builder.class)
public interface SupplierEntity {

	@Id
	@Nullable
	String getId();
	String getName();
	String getDescription();
	@Nullable
	String getThumbnailUrl();
	@Nullable
	String getFullImageUrl();
}
