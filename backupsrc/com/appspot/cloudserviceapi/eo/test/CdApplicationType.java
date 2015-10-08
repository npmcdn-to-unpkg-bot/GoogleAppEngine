package com.appspot.cloudserviceapi.eo.test;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CdApplicationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_application_type", catalog = "eol2")
public class CdApplicationType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<Application> applications = new HashSet<Application>(0);

	// Constructors

	/** default constructor */
	public CdApplicationType() {
	}

	/** minimal constructor */
	public CdApplicationType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdApplicationType(String name, Set<Application> applications) {
		this.name = name;
		this.applications = applications;
	}

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

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdApplicationType")
	public Set<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

}