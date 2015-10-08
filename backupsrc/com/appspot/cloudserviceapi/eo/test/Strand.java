package com.appspot.cloudserviceapi.eo.test;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Strand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "strand", catalog = "eol2")
public class Strand implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String description;
	private Integer sortOrder;
	private Set<ProgramGoalsRespondent> programGoalsRespondents = new HashSet<ProgramGoalsRespondent>(
			0);
	private Set<Topic> topics = new HashSet<Topic>(0);
	private Set<Assessment> assessments = new HashSet<Assessment>(0);

	// Constructors

	/** default constructor */
	public Strand() {
	}

	/** minimal constructor */
	public Strand(String name) {
		this.name = name;
	}

	/** full constructor */
	public Strand(String name, String description, Integer sortOrder,
			Set<ProgramGoalsRespondent> programGoalsRespondents,
			Set<Topic> topics, Set<Assessment> assessments) {
		this.name = name;
		this.description = description;
		this.sortOrder = sortOrder;
		this.programGoalsRespondents = programGoalsRespondents;
		this.topics = topics;
		this.assessments = assessments;
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

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "strand")
	public Set<ProgramGoalsRespondent> getProgramGoalsRespondents() {
		return this.programGoalsRespondents;
	}

	public void setProgramGoalsRespondents(
			Set<ProgramGoalsRespondent> programGoalsRespondents) {
		this.programGoalsRespondents = programGoalsRespondents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "strand")
	public Set<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "strand")
	public Set<Assessment> getAssessments() {
		return this.assessments;
	}

	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}

}