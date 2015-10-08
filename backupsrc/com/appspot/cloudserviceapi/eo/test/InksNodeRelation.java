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
 * InksNodeRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_node_relation", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "INKS_CHILD_NODE_ID" }))
public class InksNodeRelation implements java.io.Serializable {

	// Fields

	private Long id;
	private InksNode inksNodeByInksChildNodeId;
	private InksNode inksNodeByInksNodeId;
	private Integer sortOrder;
	private String status;

	// Constructors

	/** default constructor */
	public InksNodeRelation() {
	}

	/** minimal constructor */
	public InksNodeRelation(InksNode inksNodeByInksChildNodeId,
			InksNode inksNodeByInksNodeId, String status) {
		this.inksNodeByInksChildNodeId = inksNodeByInksChildNodeId;
		this.inksNodeByInksNodeId = inksNodeByInksNodeId;
		this.status = status;
	}

	/** full constructor */
	public InksNodeRelation(InksNode inksNodeByInksChildNodeId,
			InksNode inksNodeByInksNodeId, Integer sortOrder, String status) {
		this.inksNodeByInksChildNodeId = inksNodeByInksChildNodeId;
		this.inksNodeByInksNodeId = inksNodeByInksNodeId;
		this.sortOrder = sortOrder;
		this.status = status;
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
	@JoinColumn(name = "INKS_CHILD_NODE_ID", nullable = false)
	public InksNode getInksNodeByInksChildNodeId() {
		return this.inksNodeByInksChildNodeId;
	}

	public void setInksNodeByInksChildNodeId(InksNode inksNodeByInksChildNodeId) {
		this.inksNodeByInksChildNodeId = inksNodeByInksChildNodeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INKS_NODE_ID", nullable = false)
	public InksNode getInksNodeByInksNodeId() {
		return this.inksNodeByInksNodeId;
	}

	public void setInksNodeByInksNodeId(InksNode inksNodeByInksNodeId) {
		this.inksNodeByInksNodeId = inksNodeByInksNodeId;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}