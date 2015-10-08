package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * InksQuery entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_query", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "QUERY_ID" }))
public class InksQuery implements java.io.Serializable {

	// Fields

	private Long id;
	private InksNode inksNode;
	private Query query;
	private Mistake mistake;
	private Integer sortOrder;
	private String isDefault;

	// Constructors

	/** default constructor */
	public InksQuery() {
	}

	/** minimal constructor */
	public InksQuery(InksNode inksNode, Query query, Mistake mistake) {
		this.inksNode = inksNode;
		this.query = query;
		this.mistake = mistake;
	}

	/** full constructor */
	public InksQuery(InksNode inksNode, Query query, Mistake mistake,
			Integer sortOrder, String isDefault) {
		this.inksNode = inksNode;
		this.query = query;
		this.mistake = mistake;
		this.sortOrder = sortOrder;
		this.isDefault = isDefault;
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
	@JoinColumn(name = "INKS_NODE_ID", nullable = false)
	public InksNode getInksNode() {
		return this.inksNode;
	}

	public void setInksNode(InksNode inksNode) {
		this.inksNode = inksNode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QUERY_ID", nullable = false)
	public Query getQuery() {
		return this.query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MISTAKE_ID", nullable = false)
	public Mistake getMistake() {
		return this.mistake;
	}

	public void setMistake(Mistake mistake) {
		this.mistake = mistake;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "IS_DEFAULT", length = 1)
	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}