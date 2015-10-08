package com.appspot.cloudserviceapi.eo.test;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * InksConceptHierarchy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_concept_hierarchy", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_CONCEPT_ID", "PARENT_ID", "CD_PARENT_TYPE_ID" }))
public class InksConceptHierarchy implements java.io.Serializable {

	// Fields

	private Long id;
	private InksConcept inksConcept;
	private CdParentType cdParentType;
	private CdHierarchyType cdHierarchyType;
	private Long parentId;
	private Integer weight;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public InksConceptHierarchy() {
	}

	/** minimal constructor */
	public InksConceptHierarchy(Long id) {
		this.id = id;
	}

	/** full constructor */
	public InksConceptHierarchy(Long id, InksConcept inksConcept,
			CdParentType cdParentType, CdHierarchyType cdHierarchyType,
			Long parentId, Integer weight, Integer sortOrder) {
		this.id = id;
		this.inksConcept = inksConcept;
		this.cdParentType = cdParentType;
		this.cdHierarchyType = cdHierarchyType;
		this.parentId = parentId;
		this.weight = weight;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INKS_CONCEPT_ID")
	public InksConcept getInksConcept() {
		return this.inksConcept;
	}

	public void setInksConcept(InksConcept inksConcept) {
		this.inksConcept = inksConcept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_PARENT_TYPE_ID")
	public CdParentType getCdParentType() {
		return this.cdParentType;
	}

	public void setCdParentType(CdParentType cdParentType) {
		this.cdParentType = cdParentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_HIERARCHY_TYPE_ID")
	public CdHierarchyType getCdHierarchyType() {
		return this.cdHierarchyType;
	}

	public void setCdHierarchyType(CdHierarchyType cdHierarchyType) {
		this.cdHierarchyType = cdHierarchyType;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "WEIGHT")
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}