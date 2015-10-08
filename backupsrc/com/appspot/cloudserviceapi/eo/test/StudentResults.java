package com.appspot.cloudserviceapi.eo.test;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * StudentResults entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_results", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"STUDENT_ID", "SUBJECT_ID", "ADDED_DT" }))
public class StudentResults implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profile;
	private CdSubject cdSubject;
	private Long activityId;
	private String activityType;
	private Long problemId;
	private Time startTm;
	private Time endTm;
	private String problemResult;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public StudentResults() {
	}

	/** minimal constructor */
	public StudentResults(Profile profile, CdSubject cdSubject,
			Long activityId, String activityType, Time startTm, Time endTm,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy) {
		this.profile = profile;
		this.cdSubject = cdSubject;
		this.activityId = activityId;
		this.activityType = activityType;
		this.startTm = startTm;
		this.endTm = endTm;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public StudentResults(Profile profile, CdSubject cdSubject,
			Long activityId, String activityType, Long problemId, Time startTm,
			Time endTm, String problemResult, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.profile = profile;
		this.cdSubject = cdSubject;
		this.activityId = activityId;
		this.activityType = activityType;
		this.problemId = problemId;
		this.startTm = startTm;
		this.endTm = endTm;
		this.problemResult = problemResult;
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
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID", nullable = false)
	public CdSubject getCdSubject() {
		return this.cdSubject;
	}

	public void setCdSubject(CdSubject cdSubject) {
		this.cdSubject = cdSubject;
	}

	@Column(name = "ACTIVITY_ID", nullable = false)
	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column(name = "ACTIVITY_TYPE", nullable = false, length = 1)
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Column(name = "PROBLEM_ID")
	public Long getProblemId() {
		return this.problemId;
	}

	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	@Column(name = "START_TM", nullable = false, length = 8)
	public Time getStartTm() {
		return this.startTm;
	}

	public void setStartTm(Time startTm) {
		this.startTm = startTm;
	}

	@Column(name = "END_TM", nullable = false, length = 8)
	public Time getEndTm() {
		return this.endTm;
	}

	public void setEndTm(Time endTm) {
		this.endTm = endTm;
	}

	@Column(name = "PROBLEM_RESULT", length = 21)
	public String getProblemResult() {
		return this.problemResult;
	}

	public void setProblemResult(String problemResult) {
		this.problemResult = problemResult;
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