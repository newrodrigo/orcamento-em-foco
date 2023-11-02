package com.rodrigooc.orcamentoemfoco.dto;

import com.rodrigooc.orcamentoemfoco.entities.Provider;

public class ProviderDTO {

	private Long id;
	private String name;
	private String email;
	private String phone;

	public ProviderDTO() {
	}

	public ProviderDTO(Long id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public ProviderDTO(Provider entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		phone = entity.getPhone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
