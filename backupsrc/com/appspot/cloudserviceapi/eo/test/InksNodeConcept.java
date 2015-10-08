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
 * InksNodeConcept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_node_concept", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "INKS_CONCEPT_ID" }))
public class InksNodeConcept implements java.io.Serializable {

	// Fields

	private Long id;
	private InksConcept inksConcept;
	private InksNode inksNode;
	private Integer plusScore;
	private Integer minusScore;

	// Constructors

	/** default constructor */
	public InksNodeConcept() {
	}

	/** minimal constructor */
	public InksNodeConcept(Long id) {
		this.id = id;
	}

	/** full constructor */
	public InksNodeConcept(Long id, InksConcept inksConcept, InksNode inksNode,
			Integer plusScore, Integer minusScore) {
		this.id = id;
		this.inksConcept = inksConcept;
		this.inksNode = inksNode;
		this.plusScore = plusScore;
		this.minusScore = minusScore;
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
	@JoinColumn(name = "INKS_NODE_ID")
	public InksNode getInksNode() {
		return this.inksNode;
	}

	public void setInksNode(InksNode inksNode) {
		this.inksNode = inksNode;
	}

	@Column(name = "PLUS_SCORE")
	public Integer getPlusScore() {
		return this.plusScore;
	}

	public void setPlusScore(Integer plusScore) {
		this.plusScore = plusScore;
	}

	@Column(name = "MINUS_SCORE")
	public Integer getMinusScore() {
		return this.minusScore;
	}

	public void setMinusScore(Integer minusScore) {
		this.minusScore = minusScore;
	}

}