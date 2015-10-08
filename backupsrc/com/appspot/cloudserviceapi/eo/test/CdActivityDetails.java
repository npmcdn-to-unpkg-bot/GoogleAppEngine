package com.appspot.cloudserviceapi.eo.test;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CdActivityDetails implements Cloneable, Serializable {

	@Basic @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	private ActivityDetails details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	public ActivityDetails getDetails() {
		return details;
	}

	public void setDetails(ActivityDetails details) {
		this.details = details;
	}

	public Object clone() throws CloneNotSupportedException {
		CdActivityDetails clone = (CdActivityDetails)super.clone();

		return clone;
	}

	
}