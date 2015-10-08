package com.appspot.cloudserviceapi.eo.test;

import java.sql.Time;
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
 * ProgramGoalsProgramSched entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_program_sched", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ID", "SUBJECT_ID", "SCHED_DAY", "SCHED_BGN_TM" }))
public class ProgramGoalsProgramSched implements java.io.Serializable {

	// Fields

	private Long id;
	private ProgramGoalsRespondent programGoalsRespondent;
	private Long subjectId;
	private String assessmentType;
	private String schedLevel;
	private String schedDay;
	private Time schedBgnTm;
	private Time schedEndTm;

	// Constructors

	/** default constructor */
	public ProgramGoalsProgramSched() {
	}

	/** minimal constructor */
	public ProgramGoalsProgramSched(
			ProgramGoalsRespondent programGoalsRespondent, Long subjectId,
			String assessmentType, Time schedBgnTm, Time schedEndTm) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.subjectId = subjectId;
		this.assessmentType = assessmentType;
		this.schedBgnTm = schedBgnTm;
		this.schedEndTm = schedEndTm;
	}

	/** full constructor */
	public ProgramGoalsProgramSched(
			ProgramGoalsRespondent programGoalsRespondent, Long subjectId,
			String assessmentType, String schedLevel, String schedDay,
			Time schedBgnTm, Time schedEndTm) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.subjectId = subjectId;
		this.assessmentType = assessmentType;
		this.schedLevel = schedLevel;
		this.schedDay = schedDay;
		this.schedBgnTm = schedBgnTm;
		this.schedEndTm = schedEndTm;
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

	@Column(name = "SUBJECT_ID", nullable = false)
	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "ASSESSMENT_TYPE", nullable = false, length = 1)
	public String getAssessmentType() {
		return this.assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	@Column(name = "SCHED_LEVEL", length = 2)
	public String getSchedLevel() {
		return this.schedLevel;
	}

	public void setSchedLevel(String schedLevel) {
		this.schedLevel = schedLevel;
	}

	@Column(name = "SCHED_DAY", length = 4)
	public String getSchedDay() {
		return this.schedDay;
	}

	public void setSchedDay(String schedDay) {
		this.schedDay = schedDay;
	}

	@Column(name = "SCHED_BGN_TM", nullable = false, length = 8)
	public Time getSchedBgnTm() {
		return this.schedBgnTm;
	}

	public void setSchedBgnTm(Time schedBgnTm) {
		this.schedBgnTm = schedBgnTm;
	}

	@Column(name = "SCHED_END_TM", nullable = false, length = 8)
	public Time getSchedEndTm() {
		return this.schedEndTm;
	}

	public void setSchedEndTm(Time schedEndTm) {
		this.schedEndTm = schedEndTm;
	}

}