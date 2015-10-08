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
 * CdKeyType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_key_type", catalog = "eol2")
public class CdKeyType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer sortOrder;
	private Set<Key> keies = new HashSet<Key>(0);

	// Constructors

	/** default constructor */
	public CdKeyType() {
	}

	/** minimal constructor */
	public CdKeyType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdKeyType(String name, Integer sortOrder, Set<Key> keies) {
		this.name = name;
		this.sortOrder = sortOrder;
		this.keies = keies;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdKeyType")
	public Set<Key> getKeies() {
		return this.keies;
	}

	public void setKeies(Set<Key> keies) {
		this.keies = keies;
	}

}