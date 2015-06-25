package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProgramGoalsAssessmentReminder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_assessment_reminder", catalog = "eol2")
public class ProgramGoalsAssessmentReminder implements java.io.Serializable {

	// Fields

	private Long id;
	private String assessmentType;
	private String rmindType;
	private String rmindEmail;

	// Constructors

	/** default constructor */
	public ProgramGoalsAssessmentReminder() {
	}

	/** full constructor */
	public ProgramGoalsAssessmentReminder(String assessmentType,
			String rmindType, String rmindEmail) {
		this.assessmentType = assessmentType;
		this.rmindType = rmindType;
		this.rmindEmail = rmindEmail;
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

	@Column(name = "ASSESSMENT_TYPE", nullable = false, length = 1)
	public String getAssessmentType() {
		return this.assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	@Column(name = "RMIND_TYPE", nullable = false, length = 6)
	public String getRmindType() {
		return this.rmindType;
	}

	public void setRmindType(String rmindType) {
		this.rmindType = rmindType;
	}

	@Column(name = "RMIND_EMAIL", nullable = false, length = 80)
	public String getRmindEmail() {
		return this.rmindEmail;
	}

	public void setRmindEmail(String rmindEmail) {
		this.rmindEmail = rmindEmail;
	}

}