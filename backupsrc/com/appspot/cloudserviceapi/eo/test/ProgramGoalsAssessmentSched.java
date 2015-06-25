package com.appspot.cloudserviceapi.eo.test;

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
 * ProgramGoalsAssessmentSched entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_assessment_sched", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ID", "ASSESSMENT_TYPE" }))
public class ProgramGoalsAssessmentSched implements java.io.Serializable {

	// Fields

	private Long id;
	private ProgramGoalsRespondent programGoalsRespondent;
	private CdAssessmentDetails cdAssessmentDetails;
	private String assessmentType;
	private Date assessmentDt;

	// Constructors

	/** default constructor */
	public ProgramGoalsAssessmentSched() {
	}

	/** full constructor */
	public ProgramGoalsAssessmentSched(
			ProgramGoalsRespondent programGoalsRespondent,
			CdAssessmentDetails cdAssessmentDetails, String assessmentType,
			Date assessmentDt) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.cdAssessmentDetails = cdAssessmentDetails;
		this.assessmentType = assessmentType;
		this.assessmentDt = assessmentDt;
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
	@JoinColumn(name = "ID", unique = true, nullable = false, insertable = false, updatable = false)
	public ProgramGoalsRespondent getProgramGoalsRespondent() {
		return this.programGoalsRespondent;
	}

	public void setProgramGoalsRespondent(
			ProgramGoalsRespondent programGoalsRespondent) {
		this.programGoalsRespondent = programGoalsRespondent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ASSESSMENT_DETAILS_ID", nullable = false)
	public CdAssessmentDetails getCdAssessmentDetails() {
		return this.cdAssessmentDetails;
	}

	public void setCdAssessmentDetails(CdAssessmentDetails cdAssessmentDetails) {
		this.cdAssessmentDetails = cdAssessmentDetails;
	}

	@Column(name = "ASSESSMENT_TYPE", nullable = false, length = 1)
	public String getAssessmentType() {
		return this.assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ASSESSMENT_DT", nullable = false, length = 10)
	public Date getAssessmentDt() {
		return this.assessmentDt;
	}

	public void setAssessmentDt(Date assessmentDt) {
		this.assessmentDt = assessmentDt;
	}

}