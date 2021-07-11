package com.cap.backend.supplier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cap.backend.model.datastore.ImmutableSupplierEntity;
import com.cap.backend.model.datastore.SupplierEntity;
import com.cap.backend.model.hydrated.ImmutableSupplier;
import com.cap.backend.model.hydrated.Supplier;
import com.google.common.collect.ImmutableList;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class SupplierTests {

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = String.format("http://localhost:%s%s", 8080, "/supplier");
		RestAssured.requestSpecification = new RequestSpecBuilder().build();
	}

	@Test
	void create_and_list_supplier() {
		SupplierEntity supplierEntity = ImmutableSupplierEntity.builder()
				.name("supplier")
				.description("supplier description")
				.build();

		List<Supplier> existingSuppliers = Arrays.asList(
			RestAssured.given()
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/list?pageNumber=0&size=1000")
				.then()
				.statusCode(200)
				.log()
				.body(true)
				.extract()
				.as(Supplier[].class)
		);

		List<Supplier> createdSupplier  = Arrays.asList(
		RestAssured.given()
				.header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
				.body(ImmutableList.builder()
						.add(supplierEntity)
				.build())
				.when()
				.post("/create-or-update")
				.then()
				.statusCode(200)
				.log()
				.body(true)
				.extract()
				.as(Supplier[].class)
		);

		Assertions.assertEquals(createdSupplier.size(), 1);
		Assertions.assertEquals(createdSupplier.get(0).getName(), supplierEntity.getName());
		Assertions.assertEquals(createdSupplier.get(0).getDescription(), supplierEntity.getDescription());
		Assertions.assertNull(createdSupplier.get(0).getThumbnailUrl());
		Assertions.assertNull(createdSupplier.get(0).getFullImageUrl());
		for (Supplier s : existingSuppliers) {
			Assertions.assertNotEquals(s.getId(), createdSupplier.get(0).getId());
		}

		List<Supplier> listedSupplier  = Arrays.asList(
		RestAssured.given()
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/list?pageNumber=0&size=1000")
				.then()
				.statusCode(200)
                .log()
				.body(true)
				.extract()
				.as(Supplier[].class)
		);

		Assertions.assertEquals(
			listedSupplier,
			ImmutableList.builder().addAll(existingSuppliers).addAll(createdSupplier).build()
		);
	}
}
