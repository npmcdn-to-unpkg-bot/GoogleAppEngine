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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Application entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "application", catalog = "eol2")
public class Application implements java.io.Serializable {

	// Fields

	private Long id;
	private CdApplicationType cdApplicationType;
	private String name;
	private String description;
	private Integer sortOrder;
	private String ssoMethod;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors

	/** default constructor */
	public Application() {
	}

	/** minimal constructor */
	public Application(CdApplicationType cdApplicationType, String name) {
		this.cdApplicationType = cdApplicationType;
		this.name = name;
	}

	/** full constructor */
	public Application(CdApplicationType cdApplicationType, String name,
			String description, Integer sortOrder, String ssoMethod,
			Set<Role> roles) {
		this.cdApplicationType = cdApplicationType;
		this.name = name;
		this.description = description;
		this.sortOrder = sortOrder;
		this.ssoMethod = ssoMethod;
		this.roles = roles;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APP_TYPE_ID", nullable = false)
	public CdApplicationType getCdApplicationType() {
		return this.cdApplicationType;
	}

	public void setCdApplicationType(CdApplicationType cdApplicationType) {
		this.cdApplicationType = cdApplicationType;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "SSO_METHOD", length = 1)
	public String getSsoMethod() {
		return this.ssoMethod;
	}

	public void setSsoMethod(String ssoMethod) {
		this.ssoMethod = ssoMethod;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "application")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}