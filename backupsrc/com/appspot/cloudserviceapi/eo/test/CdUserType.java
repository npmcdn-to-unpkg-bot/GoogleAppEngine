package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CdUserType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_user_type", catalog = "eol2")
public class CdUserType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public CdUserType() {
	}

	/** minimal constructor */
	public CdUserType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdUserType(String name, Integer sortOrder) {
		this.name = name;
		this.sortOrder = sortOrder;
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

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}