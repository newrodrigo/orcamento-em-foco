package com.rodrigooc.orcamentoemfoco.dto;

import com.rodrigooc.orcamentoemfoco.entities.Category;

public class CategoryDTO {

	private Long id;
	private String description;

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public CategoryDTO(Category entity) {
		id = entity.getId();
		description = entity.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
