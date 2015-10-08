package com.appspot.cloudserviceapi.sgc.dao;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WorkOrderComposedIdKey implements Serializable {
	public String lastName;
	public String firstName;
	public int phone;

	public WorkOrderComposedIdKey() {
	}

	public WorkOrderComposedIdKey(String lastName, String firstName, int phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + phone;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkOrderComposedIdKey other = (WorkOrderComposedIdKey) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone != other.phone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkOrderComposedIdKey [lastName=" + lastName + ", firstName="
				+ firstName + ", phone=" + phone + "]";
	}

}