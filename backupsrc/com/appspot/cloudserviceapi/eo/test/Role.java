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
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "eol2")
public class Role implements java.io.Serializable {

	// Fields

	private Long id;
	private Application application;
	private CdRoleType cdRoleType;
	private String name;
	private String description;
	private Integer sortOrder;
	private Set<RoleOrgPair> roleOrgPairs = new HashSet<RoleOrgPair>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Application application, CdRoleType cdRoleType, String name) {
		this.application = application;
		this.cdRoleType = cdRoleType;
		this.name = name;
	}

	/** full constructor */
	public Role(Application application, CdRoleType cdRoleType, String name,
			String description, Integer sortOrder, Set<RoleOrgPair> roleOrgPairs) {
		this.application = application;
		this.cdRoleType = cdRoleType;
		this.name = name;
		this.description = description;
		this.sortOrder = sortOrder;
		this.roleOrgPairs = roleOrgPairs;
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
	@JoinColumn(name = "APPLICATION_ID", nullable = false)
	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ROLE_TYPE_ID", nullable = false)
	public CdRoleType getCdRoleType() {
		return this.cdRoleType;
	}

	public void setCdRoleType(CdRoleType cdRoleType) {
		this.cdRoleType = cdRoleType;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<RoleOrgPair> getRoleOrgPairs() {
		return this.roleOrgPairs;
	}

	public void setRoleOrgPairs(Set<RoleOrgPair> roleOrgPairs) {
		this.roleOrgPairs = roleOrgPairs;
	}

}