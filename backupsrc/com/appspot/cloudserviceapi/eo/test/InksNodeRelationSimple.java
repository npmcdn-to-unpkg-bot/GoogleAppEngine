package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * InksNodeRelationSimple entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_node_relation", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "INKS_CHILD_NODE_ID" }))
		

@SqlResultSetMapping(name="inks_node_relation_simple", entities=@EntityResult(entityClass=InksNodeRelationSimple.class))
@NamedNativeQueries({
	/*Retrieve child nodes*/
	@NamedNativeQuery(name="get_all_childs", 
			query="select nr.* from inks_node_relation nr " +
					"where nr.INKS_NODE_ID = :nodeId " +
					"order by nr.sort_order", resultSetMapping="inks_node_relation_simple")
	}
)		
		
		
public class InksNodeRelationSimple implements java.io.Serializable {

	// Fields

	private Long id;
	private Long inksNodeIdByInksChildNodeId;
	private Long inksNodeIdByInksNodeId;
	private Integer sortOrder;
	private String status;

	// Constructors

	/** default constructor */
	public InksNodeRelationSimple() {
	}

	/** minimal constructor */
	public InksNodeRelationSimple(Long inksNodeIdByInksChildNodeId,
			Long inksNodeIdByInksNodeId, String status) {
		this.inksNodeIdByInksChildNodeId = inksNodeIdByInksChildNodeId;
		this.inksNodeIdByInksNodeId = inksNodeIdByInksNodeId;
		this.status = status;
	}

	/** full constructor */
	public InksNodeRelationSimple(Long inksNodeIdByInksChildNodeId,
			Long inksNodeIdByInksNodeId, Integer sortOrder, String status) {
		this.inksNodeIdByInksChildNodeId = inksNodeIdByInksChildNodeId;
		this.inksNodeIdByInksNodeId = inksNodeIdByInksNodeId;
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

	@Column(name = "INKS_CHILD_NODE_ID", nullable = false)
	public Long getInksNodeIdByInksChildNodeId() {
		return this.inksNodeIdByInksChildNodeId;
	}

	public void setInksNodeIdByInksChildNodeId(Long inksNodeIdByInksChildNodeId) {
		this.inksNodeIdByInksChildNodeId = inksNodeIdByInksChildNodeId;
	}

	@Column(name = "INKS_NODE_ID", nullable = false)
	public Long getInksNodeIdByInksNodeId() {
		return this.inksNodeIdByInksNodeId;
	}

	public void setInksNodeIdByInksNodeId(Long inksNodeIdByInksNodeId) {
		this.inksNodeIdByInksNodeId = inksNodeIdByInksNodeId;
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