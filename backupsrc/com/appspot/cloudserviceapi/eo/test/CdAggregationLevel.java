package com.appspot.cloudserviceapi.eo.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CdAggregationLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_aggregation_level", catalog = "eol2")
public class CdAggregationLevel implements
		java.io.Serializable {

	// Fields

	private Long aggregationLevelId;
	private String name;
	private String status;
	private Integer sortOrder;
	private Set<StudentStandardResult> studentStandardResults = new HashSet<StudentStandardResult>(
			0);

	// Constructors

	/** default constructor */
	public CdAggregationLevel() {
	}

	/** minimal constructor */
	public CdAggregationLevel(Long aggregationLevelId, String name) {
		this.aggregationLevelId = aggregationLevelId;
		this.name = name;
	}

	/** full constructor */
	public CdAggregationLevel(Long aggregationLevelId, String name,
			String status, Integer sortOrder,
			Set<StudentStandardResult> studentStandardResults) {
		this.aggregationLevelId = aggregationLevelId;
		this.name = name;
		this.status = status;
		this.sortOrder = sortOrder;
		this.studentStandardResults = studentStandardResults;
	}

	// Property accessors
	@Id
	@Column(name = "AGGREGATION_LEVEL_ID", unique = true, nullable = false)
	public Long getAggregationLevelId() {
		return this.aggregationLevelId;
	}

	public void setAggregationLevelId(Long aggregationLevelId) {
		this.aggregationLevelId = aggregationLevelId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdAggregationLevel")
	public Set<StudentStandardResult> getStudentStandardResults() {
		return this.studentStandardResults;
	}

	public void setStudentStandardResults(
			Set<StudentStandardResult> studentStandardResults) {
		this.studentStandardResults = studentStandardResults;
	}


}
