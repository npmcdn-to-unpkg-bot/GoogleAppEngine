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
 * CdAlertType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_alert_type", catalog = "eol2")
public class CdAlertType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String description;
	private Set<AlertLog> alertLogs = new HashSet<AlertLog>(0);

	// Constructors

	/** default constructor */
	public CdAlertType() {
	}

	/** minimal constructor */
	public CdAlertType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/** full constructor */
	public CdAlertType(String name, String description, Set<AlertLog> alertLogs) {
		this.name = name;
		this.description = description;
		this.alertLogs = alertLogs;
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

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdAlertType")
	public Set<AlertLog> getAlertLogs() {
		return this.alertLogs;
	}

	public void setAlertLogs(Set<AlertLog> alertLogs) {
		this.alertLogs = alertLogs;
	}

}