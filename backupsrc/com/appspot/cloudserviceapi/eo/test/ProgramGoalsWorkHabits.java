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
 * ProgramGoalsWorkHabits entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_work_habits", catalog = "eol2")
public class ProgramGoalsWorkHabits implements java.io.Serializable {

	// Fields

	private Long id;
	private ProgramGoalsRespondent programGoalsRespondent;
	private Short completesAssignments;
	private Short showsSolution;
	private Short focus;
	private Short accuracy;
	private Short organization;
	private Short preparation;

	// Constructors

	/** default constructor */
	public ProgramGoalsWorkHabits() {
	}

	/** full constructor */
	public ProgramGoalsWorkHabits(
			ProgramGoalsRespondent programGoalsRespondent,
			Short completesAssignments, Short showsSolution, Short focus,
			Short accuracy, Short organization, Short preparation) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.completesAssignments = completesAssignments;
		this.showsSolution = showsSolution;
		this.focus = focus;
		this.accuracy = accuracy;
		this.organization = organization;
		this.preparation = preparation;
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

	@Column(name = "COMPLETES_ASSIGNMENTS", nullable = false)
	public Short getCompletesAssignments() {
		return this.completesAssignments;
	}

	public void setCompletesAssignments(Short completesAssignments) {
		this.completesAssignments = completesAssignments;
	}

	@Column(name = "SHOWS_SOLUTION", nullable = false)
	public Short getShowsSolution() {
		return this.showsSolution;
	}

	public void setShowsSolution(Short showsSolution) {
		this.showsSolution = showsSolution;
	}

	@Column(name = "FOCUS", nullable = false)
	public Short getFocus() {
		return this.focus;
	}

	public void setFocus(Short focus) {
		this.focus = focus;
	}

	@Column(name = "ACCURACY", nullable = false)
	public Short getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(Short accuracy) {
		this.accuracy = accuracy;
	}

	@Column(name = "ORGANIZATION", nullable = false)
	public Short getOrganization() {
		return this.organization;
	}

	public void setOrganization(Short organization) {
		this.organization = organization;
	}

	@Column(name = "PREPARATION", nullable = false)
	public Short getPreparation() {
		return this.preparation;
	}

	public void setPreparation(Short preparation) {
		this.preparation = preparation;
	}

}