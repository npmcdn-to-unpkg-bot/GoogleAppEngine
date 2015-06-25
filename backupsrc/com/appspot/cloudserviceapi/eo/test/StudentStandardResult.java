package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * StudentStandardResult entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_standard_result", catalog = "eol2" ,uniqueConstraints = @UniqueConstraint(columnNames = {
		"PROFILE_ID", "AGGREGATION_LEVEL_ID","STANDARD_ID" }))
		

@SqlResultSetMapping(name="student_standard_result", entities=@EntityResult(entityClass=StudentStandardResult.class))
@NamedNativeQueries({
	/*Does this help*/
	@NamedNativeQuery(name="slcs", query="select s.* from student_standard_result s", resultSetMapping="student_standard_result"
)

}
)		
		
		
public class StudentStandardResult implements java.io.Serializable {

	// Fields

	private Long id;
	private CdAggregationLevel cdAggregationLevel;
	private Profile profile;
	private Long standardId;
	private String resultInd;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public StudentStandardResult() {
	}

	/** minimal constructor */
	public StudentStandardResult(CdAggregationLevel cdAggregationLevel,
			Profile profile, Long standardId, Long addedBy, Long updatedBy) {
		this.cdAggregationLevel = cdAggregationLevel;
		this.profile = profile;
		this.standardId = standardId;
		this.addedBy = addedBy;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public StudentStandardResult(CdAggregationLevel cdAggregationLevel,
			Profile profile, Long standardId, String resultInd, Date addedDt,
			Long addedBy, Date updatedDt, Long updatedBy) {
		this.cdAggregationLevel = cdAggregationLevel;
		this.profile = profile;
		this.standardId = standardId;
		this.resultInd = resultInd;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
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
	@JoinColumn(name = "AGGREGATION_LEVEL_ID", nullable = false)
	public CdAggregationLevel getCdAggregationLevel() {
		return this.cdAggregationLevel;
	}

	public void setCdAggregationLevel(CdAggregationLevel cdAggregationLevel) {
		this.cdAggregationLevel = cdAggregationLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_ID", nullable = false)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Column(name = "STANDARD_ID", nullable = false)
	public Long getStandardId() {
		return this.standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	@Column(name = "RESULT_IND", length = 1)
	public String getResultInd() {
		return this.resultInd;
	}

	public void setResultInd(String resultInd) {
		this.resultInd = resultInd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", length = 10)
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
	@Column(name = "UPDATED_DT", length = 10)
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