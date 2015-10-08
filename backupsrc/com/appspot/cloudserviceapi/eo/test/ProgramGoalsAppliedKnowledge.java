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
 * ProgramGoalsAppliedKnowledge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_applied_knowledge", catalog = "eol2")
public class ProgramGoalsAppliedKnowledge implements java.io.Serializable {

	// Fields

	private Long id;
	private ProgramGoalsRespondent programGoalsRespondent;
	private Short appliesKnowledgeRealWorld;
	private Short solvesIntegratedProblems;

	// Constructors

	/** default constructor */
	public ProgramGoalsAppliedKnowledge() {
	}

	/** full constructor */
	public ProgramGoalsAppliedKnowledge(
			ProgramGoalsRespondent programGoalsRespondent,
			Short appliesKnowledgeRealWorld, Short solvesIntegratedProblems) {
		this.programGoalsRespondent = programGoalsRespondent;
		this.appliesKnowledgeRealWorld = appliesKnowledgeRealWorld;
		this.solvesIntegratedProblems = solvesIntegratedProblems;
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

	@Column(name = "APPLIES_KNOWLEDGE_REAL_WORLD", nullable = false)
	public Short getAppliesKnowledgeRealWorld() {
		return this.appliesKnowledgeRealWorld;
	}

	public void setAppliesKnowledgeRealWorld(Short appliesKnowledgeRealWorld) {
		this.appliesKnowledgeRealWorld = appliesKnowledgeRealWorld;
	}

	@Column(name = "SOLVES_INTEGRATED_PROBLEMS", nullable = false)
	public Short getSolvesIntegratedProblems() {
		return this.solvesIntegratedProblems;
	}

	public void setSolvesIntegratedProblems(Short solvesIntegratedProblems) {
		this.solvesIntegratedProblems = solvesIntegratedProblems;
	}

}