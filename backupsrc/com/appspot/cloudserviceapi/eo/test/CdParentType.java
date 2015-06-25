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
 * CdParentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_parent_type", catalog = "eol2")
public class CdParentType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String description;
	private Set<InksConceptHierarchy> inksConceptHierarchies = new HashSet<InksConceptHierarchy>(
			0);

	// Constructors

	/** default constructor */
	public CdParentType() {
	}

	/** minimal constructor */
	public CdParentType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdParentType(String name, String description,
			Set<InksConceptHierarchy> inksConceptHierarchies) {
		this.name = name;
		this.description = description;
		this.inksConceptHierarchies = inksConceptHierarchies;
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

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdParentType")
	public Set<InksConceptHierarchy> getInksConceptHierarchies() {
		return this.inksConceptHierarchies;
	}

	public void setInksConceptHierarchies(
			Set<InksConceptHierarchy> inksConceptHierarchies) {
		this.inksConceptHierarchies = inksConceptHierarchies;
	}

}