package com.bkper.server.api;

import java.util.Date;

public class TempTransaction {

	private Date informedDate;

	private String description;

	public Date getInformedDate() {
		return informedDate;
	}

	public void setInformedDate(Date informedDate) {
		this.informedDate = informedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}