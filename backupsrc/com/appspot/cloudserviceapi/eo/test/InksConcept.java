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
import javax.persistence.UniqueConstraint;

/**
 * InksConcept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_concept", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class InksConcept implements java.io.Serializable {

	// Fields

	private Long id;
	private CdConceptType cdConceptType;
	private String name;
	private String description;
	private Set<InksNodeConcept> inksNodeConcepts = new HashSet<InksNodeConcept>(
			0);
	private Set<InksConceptHierarchy> inksConceptHierarchies = new HashSet<InksConceptHierarchy>(
			0);

	// Constructors

	/** default constructor */
	public InksConcept() {
	}

	/** minimal constructor */
	public InksConcept(CdConceptType cdConceptType, String name) {
		this.cdConceptType = cdConceptType;
		this.name = name;
	}

	/** full constructor */
	public InksConcept(CdConceptType cdConceptType, String name,
			String description, Set<InksNodeConcept> inksNodeConcepts,
			Set<InksConceptHierarchy> inksConceptHierarchies) {
		this.cdConceptType = cdConceptType;
		this.name = name;
		this.description = description;
		this.inksNodeConcepts = inksNodeConcepts;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_CONCEPT_TYPE_ID", nullable = false)
	public CdConceptType getCdConceptType() {
		return this.cdConceptType;
	}

	public void setCdConceptType(CdConceptType cdConceptType) {
		this.cdConceptType = cdConceptType;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksConcept")
	public Set<InksNodeConcept> getInksNodeConcepts() {
		return this.inksNodeConcepts;
	}

	public void setInksNodeConcepts(Set<InksNodeConcept> inksNodeConcepts) {
		this.inksNodeConcepts = inksNodeConcepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksConcept")
	public Set<InksConceptHierarchy> getInksConceptHierarchies() {
		return this.inksConceptHierarchies;
	}

	public void setInksConceptHierarchies(
			Set<InksConceptHierarchy> inksConceptHierarchies) {
		this.inksConceptHierarchies = inksConceptHierarchies;
	}

}