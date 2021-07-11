package com.cap.backend.model.hydrated;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableSupplier.class)
@JsonDeserialize(builder = ImmutableSupplier.Builder.class)
public interface Supplier {

	String getId();
	String getName();
	String getDescription();
	@Nullable
	String getThumbnailUrl();
	@Nullable
	String getFullImageUrl();
}
