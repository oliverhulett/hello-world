package com.example.sdmdb.model;

import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

@Document
@Value.Immutable
public interface Customer {

	@Id
	@Nullable
	public String getId();

	public String getFirstName();

	public String getLastName();
}
