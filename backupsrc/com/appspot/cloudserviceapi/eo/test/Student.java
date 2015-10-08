package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.google.appengine.api.datastore.Key;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "eol2")
public class Student implements java.io.Serializable {

	// Fields

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private Long profileId;
	private Integer grade;

	// Constructors

/*	//** default constructor *//*
	public Student() {
	}

	//** minimal constructor *//*
	public Student(Long profileId) {
		this.profileId = profileId;
	}

	//** full constructor *//*
	public Student(Long profileId, Integer grade) {
		this.profileId = profileId;
		this.grade = grade;
	}
*/

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROFILE_ID", nullable = false)
	public Long getProfileId() {
		return this.profileId;
	}

	@Validate("required")
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Column(name = "GRADE")
	public Integer getGrade() {
		return this.grade;
	}

	@Validate("required, min=0, max=100")
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}