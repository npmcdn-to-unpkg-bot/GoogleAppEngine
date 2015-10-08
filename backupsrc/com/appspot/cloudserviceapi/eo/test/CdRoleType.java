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
 * CdRoleType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_role_type", catalog = "eol2")
public class CdRoleType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors

	/** default constructor */
	public CdRoleType() {
	}

	/** minimal constructor */
	public CdRoleType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdRoleType(String name, Set<Role> roles) {
		this.name = name;
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

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdRoleType")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}