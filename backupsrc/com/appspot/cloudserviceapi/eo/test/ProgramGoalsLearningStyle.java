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
 * ProgramGoalsLearningStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_learning_style", catalog = "eol2")
public class ProgramGoalsLearningStyle implements java.io.Serializable {

	// Fields

	private Long id;
	private ProgramGoalsRespondent programGoalsRespondent;
	private Short anyStyle;
	private Short realWorldExamples;
	private Short simplerExplanations;
	private Short visual;
	private Short auditory;
	private Short handsOn;
	private Short deductive;
	private Short inductive;

	// Constructors

	/** default constructor */
	public ProgramGoalsLearningStyle() {
	}

	/** full constructor */
	public ProgramGoalsLearningStyle(
			ProgramGoalsRespondent programGoalsRespondent, Short anyStyle,
			Short realWorldExamples, Short simplerExplanations, Short visual,
			Short auditory, Short handsOn, Short deductive, Short inductive) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.anyStyle = anyStyle;
		this.realWorldExamples = realWorldExamples;
		this.simplerExplanations = simplerExplanations;
		this.visual = visual;
		this.auditory = auditory;
		this.handsOn = handsOn;
		this.deductive = deductive;
		this.inductive = inductive;
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

	@Column(name = "ANY_STYLE", nullable = false)
	public Short getAnyStyle() {
		return this.anyStyle;
	}

	public void setAnyStyle(Short anyStyle) {
		this.anyStyle = anyStyle;
	}

	@Column(name = "REAL_WORLD_EXAMPLES", nullable = false)
	public Short getRealWorldExamples() {
		return this.realWorldExamples;
	}

	public void setRealWorldExamples(Short realWorldExamples) {
		this.realWorldExamples = realWorldExamples;
	}

	@Column(name = "SIMPLER_EXPLANATIONS", nullable = false)
	public Short getSimplerExplanations() {
		return this.simplerExplanations;
	}

	public void setSimplerExplanations(Short simplerExplanations) {
		this.simplerExplanations = simplerExplanations;
	}

	@Column(name = "VISUAL", nullable = false)
	public Short getVisual() {
		return this.visual;
	}

	public void setVisual(Short visual) {
		this.visual = visual;
	}

	@Column(name = "AUDITORY", nullable = false)
	public Short getAuditory() {
		return this.auditory;
	}

	public void setAuditory(Short auditory) {
		this.auditory = auditory;
	}

	@Column(name = "HANDS_ON", nullable = false)
	public Short getHandsOn() {
		return this.handsOn;
	}

	public void setHandsOn(Short handsOn) {
		this.handsOn = handsOn;
	}

	@Column(name = "DEDUCTIVE", nullable = false)
	public Short getDeductive() {
		return this.deductive;
	}

	public void setDeductive(Short deductive) {
		this.deductive = deductive;
	}

	@Column(name = "INDUCTIVE", nullable = false)
	public Short getInductive() {
		return this.inductive;
	}

	public void setInductive(Short inductive) {
		this.inductive = inductive;
	}

}