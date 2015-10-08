package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Basic;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.appengine.api.datastore.Key;

/**
 * AssessmentDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment_detail", catalog = "eol2")
public class AssessmentDetail implements java.io.Serializable {

	// Fields

	@Basic @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    //private Long id;	//can't be Long due to GAE/J JPA child limitation (error: Cannot have a java.lang.Long primary key and be a child object)
    private Key id;
	private Assessment assessment;
	private AssessmentItem assessmentItem;
	private Integer sortOrder;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public AssessmentDetail() {
	}

	/** minimal constructor */
	public AssessmentDetail(Integer sortOrder, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.sortOrder = sortOrder;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public AssessmentDetail(Assessment assessment,
			AssessmentItem assessmentItem, Integer sortOrder, Date addedDt,
			Long addedBy, Date updatedDt, Long updatedBy) {
		this.assessment = assessment;
		this.assessmentItem = assessmentItem;
		this.sortOrder = sortOrder;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	// Property accessors
/*	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
*/
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}
 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESSMENT_ID")
	public Assessment getAssessment() {
		return this.assessment;
	}


	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESSMENT_ITEM_ID")
	public AssessmentItem getAssessmentItem() {
		return this.assessmentItem;
	}

	public void setAssessmentItem(AssessmentItem assessmentItem) {
		this.assessmentItem = assessmentItem;
	}

	@Column(name = "SORT_ORDER", nullable = false)
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

}