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
 * ProgramGoalsGradeScale entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_grade_scale", catalog = "eol2")
public class ProgramGoalsGradeScale implements java.io.Serializable {

	// Fields

	private Long id;
	private ProgramGoalsRespondent programGoalsRespondent;
	private String gradeScale;
	private Double gradeUpperBnd;
	private Double gradeLowerBnd;

	// Constructors

	/** default constructor */
	public ProgramGoalsGradeScale() {
	}

	/** minimal constructor */
	public ProgramGoalsGradeScale(
			ProgramGoalsRespondent programGoalsRespondent,
			Double gradeUpperBnd, Double gradeLowerBnd) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.gradeUpperBnd = gradeUpperBnd;
		this.gradeLowerBnd = gradeLowerBnd;
	}

	/** full constructor */
	public ProgramGoalsGradeScale(
			ProgramGoalsRespondent programGoalsRespondent, String gradeScale,
			Double gradeUpperBnd, Double gradeLowerBnd) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.gradeScale = gradeScale;
		this.gradeUpperBnd = gradeUpperBnd;
		this.gradeLowerBnd = gradeLowerBnd;
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

	@Column(name = "GRADE_SCALE", length = 3)
	public String getGradeScale() {
		return this.gradeScale;
	}

	public void setGradeScale(String gradeScale) {
		this.gradeScale = gradeScale;
	}

	@Column(name = "GRADE_UPPER_BND", nullable = false, precision = 5)
	public Double getGradeUpperBnd() {
		return this.gradeUpperBnd;
	}

	public void setGradeUpperBnd(Double gradeUpperBnd) {
		this.gradeUpperBnd = gradeUpperBnd;
	}

	@Column(name = "GRADE_LOWER_BND", nullable = false, precision = 5)
	public Double getGradeLowerBnd() {
		return this.gradeLowerBnd;
	}

	public void setGradeLowerBnd(Double gradeLowerBnd) {
		this.gradeLowerBnd = gradeLowerBnd;
	}

}