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

/**
 * ProgramGoalsParentTarget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_parent_target", catalog = "eol2")
public class ProgramGoalsParentTarget implements java.io.Serializable {

	// Fields

	private Long id;
	private CdCollegeType cdCollegeType;
	private ProgramGoalsRespondent programGoalsRespondent;
	private Double gradePctgTarget;
	private Double gradePctgLastYr;
	private Double gradePctgAvg;
	private String college;
	private Short collegeYr;

	// Constructors

	/** default constructor */
	public ProgramGoalsParentTarget() {
	}

	/** minimal constructor */
	public ProgramGoalsParentTarget(
			ProgramGoalsRespondent programGoalsRespondent,
			Double gradePctgTarget, Double gradePctgLastYr,
			Double gradePctgAvg, String college) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.gradePctgTarget = gradePctgTarget;
		this.gradePctgLastYr = gradePctgLastYr;
		this.gradePctgAvg = gradePctgAvg;
		this.college = college;
	}

	/** full constructor */
	public ProgramGoalsParentTarget(CdCollegeType cdCollegeType,
			ProgramGoalsRespondent programGoalsRespondent,
			Double gradePctgTarget, Double gradePctgLastYr,
			Double gradePctgAvg, String college, Short collegeYr) {
		this.cdCollegeType = cdCollegeType;
		this.programGoalsRespondent = programGoalsRespondent;
		this.gradePctgTarget = gradePctgTarget;
		this.gradePctgLastYr = gradePctgLastYr;
		this.gradePctgAvg = gradePctgAvg;
		this.college = college;
		this.collegeYr = collegeYr;
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
	@JoinColumn(name = "CD_COLLEGE_TYPE_ID")
	public CdCollegeType getCdCollegeType() {
		return this.cdCollegeType;
	}

	public void setCdCollegeType(CdCollegeType cdCollegeType) {
		this.cdCollegeType = cdCollegeType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROGRAM_GOALS_RESPONDENT_ID", nullable = false)
	public ProgramGoalsRespondent getProgramGoalsRespondent() {
		return this.programGoalsRespondent;
	}

	public void setProgramGoalsRespondent(
			ProgramGoalsRespondent programGoalsRespondent) {
		this.programGoalsRespondent = programGoalsRespondent;
	}

	@Column(name = "GRADE_PCTG_TARGET", nullable = false, precision = 5)
	public Double getGradePctgTarget() {
		return this.gradePctgTarget;
	}

	public void setGradePctgTarget(Double gradePctgTarget) {
		this.gradePctgTarget = gradePctgTarget;
	}

	@Column(name = "GRADE_PCTG_LAST_YR", nullable = false, precision = 5)
	public Double getGradePctgLastYr() {
		return this.gradePctgLastYr;
	}

	public void setGradePctgLastYr(Double gradePctgLastYr) {
		this.gradePctgLastYr = gradePctgLastYr;
	}

	@Column(name = "GRADE_PCTG_AVG", nullable = false, precision = 5)
	public Double getGradePctgAvg() {
		return this.gradePctgAvg;
	}

	public void setGradePctgAvg(Double gradePctgAvg) {
		this.gradePctgAvg = gradePctgAvg;
	}

	@Column(name = "COLLEGE", nullable = false, length = 6)
	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Column(name = "COLLEGE_YR")
	public Short getCollegeYr() {
		return this.collegeYr;
	}

	public void setCollegeYr(Short collegeYr) {
		this.collegeYr = collegeYr;
	}

}