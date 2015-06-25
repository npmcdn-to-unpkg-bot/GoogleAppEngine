package com.aurifa.struts2.tutorial.model;

import java.io.Serializable;

public class Business implements Serializable {
	Integer id;

	String name;

	public Business() {
	}

	public Business(Integer businessId, String name) {
		this.id = businessId;
		this.name = name;
	}

	public Integer getBusinessId() {
		return id;
	}

	public void setBusinessId(Integer businessId) {
		this.id = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
