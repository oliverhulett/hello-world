package com.cap.backend.datastore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String image;
	@Column(nullable = false)
	private String thumbnail;

	public String getDescription() {
		return description;
	}
	public long getId() {
		return id;
	}
	public String getImage() {
		return image;
	}
	public String getName() {
		return name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}