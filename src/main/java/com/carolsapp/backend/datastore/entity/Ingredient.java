package com.carolsapp.backend.datastore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
@Value.Style(create = "new", beanFriendlyModifiables = true)
@Entity
public abstract class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public abstract long getId();

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getImage();

	public abstract String getThumbnail();
}