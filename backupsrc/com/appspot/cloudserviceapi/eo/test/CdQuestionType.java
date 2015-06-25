package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CdQuestionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_question_type", catalog = "eol2")
public class CdQuestionType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer sortOrder;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<AssessmentItem> assessmentItems = new HashSet<AssessmentItem>(0);

	// Constructors

	/** default constructor */
	public CdQuestionType() {
	}

	/** minimal constructor */
	public CdQuestionType(String name, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.name = name;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public CdQuestionType(String name, Integer sortOrder, Date addedDt,
			Long addedBy, Date updatedDt, Long updatedBy,
			Set<AssessmentItem> assessmentItems) {
		this.name = name;
		this.sortOrder = sortOrder;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.assessmentItems = assessmentItems;
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

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", nullable = false, length = 10)
	public Date getAddedDt() {
		return this.addedDt;
	}

	public void setAddedDt(Date addedDt) {
		this.addedDt = addedDt;
	}

	@Column(name = "ADDED_BY", nullable = false)
	public Long getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(Long addedBy) {
		this.addedBy = addedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DT", nullable = false, length = 10)
	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@Column(name = "UPDATED_BY", nullable = false)
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdQuestionType")
	public Set<AssessmentItem> getAssessmentItems() {
		return this.assessmentItems;
	}

	public void setAssessmentItems(Set<AssessmentItem> assessmentItems) {
		this.assessmentItems = assessmentItems;
	}

}