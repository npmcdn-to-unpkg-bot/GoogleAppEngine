package com.appspot.cloudserviceapi.sgc;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable(identityType = IdentityType.APPLICATION
//		, objectIdClass = WorkOrderComposedIdKey.class
) 
public class MockupWorkOrder {

    @Persistent
//    @PrimaryKey
	private String lastName = "lastName";
    @Persistent
//    @PrimaryKey
	private String firstName = "firstName";
    @Persistent
	private String email = "test@domain.com";
    @Persistent(primaryKey="true")
//	private int phone;
	private String phone = "571-2257054";
    @Persistent
	private int alternatePhone;
    @Persistent
	private Date dateRequested = new Date();
    @Persistent
	private Date datePerformed = new Date();
    @Persistent
	private String address1 = "8112 Prex Dr";
    @Persistent
	private String address2 = "";
    @Persistent
	private String city = "Falls Church";
    @Persistent
	private String state = "VA";
    @Persistent
	private String postal = "22180";
    @Persistent
	private String country = "United States";
    
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	//	public int getPhone() {
//		return phone;
//	}
//	public void setPhone(int phone) {
//		this.phone = phone;
//	}
	public int getAlternatePhone() {
		return alternatePhone;
	}
	public void setAlternatePhone(int alternatePhone) {
		this.alternatePhone = alternatePhone;
	}
	public Date getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	public Date getDatePerformed() {
		return datePerformed;
	}
	public void setDatePerformed(Date datePerformed) {
		this.datePerformed = datePerformed;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
