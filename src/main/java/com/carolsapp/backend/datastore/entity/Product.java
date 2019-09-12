package com.carolsapp.backend.datastore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
@Value.Style(privateNoargConstructor = true, create = "new", beanFriendlyModifiables = true, passAnnotations = {
		Inheritance.class, Entity.class, Id.class, GeneratedValue.class })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public abstract long getId();

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getImage();

	public abstract String getThumbnail();
}