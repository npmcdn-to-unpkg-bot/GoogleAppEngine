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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Goal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goal", catalog = "eol2")
public class Goal implements java.io.Serializable {

	// Fields

	private Long id;
	private Topic topic;
	private String text;
	private Integer sortOrder;
	private Set<LearningObjective> learningObjectives = new HashSet<LearningObjective>(
			0);
	private Set<TermDefinition> termDefinitions = new HashSet<TermDefinition>(0);

	// Constructors

	/** default constructor */
	public Goal() {
	}

	/** minimal constructor */
	public Goal(Topic topic, String text, Integer sortOrder) {
		this.topic = topic;
		this.text = text;
		this.sortOrder = sortOrder;
	}

	/** full constructor */
	public Goal(Topic topic, String text, Integer sortOrder,
			Set<LearningObjective> learningObjectives,
			Set<TermDefinition> termDefinitions) {
		this.topic = topic;
		this.text = text;
		this.sortOrder = sortOrder;
		this.learningObjectives = learningObjectives;
		this.termDefinitions = termDefinitions;
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

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "TOPIC_ID", nullable = false)
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Column(name = "TEXT", nullable = false, length = 250)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "SORT_ORDER", nullable = false)
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goal")
	public Set<LearningObjective> getLearningObjectives() {
		return this.learningObjectives;
	}

	public void setLearningObjectives(Set<LearningObjective> learningObjectives) {
		this.learningObjectives = learningObjectives;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goal")
	public Set<TermDefinition> getTermDefinitions() {
		return this.termDefinitions;
	}

	public void setTermDefinitions(Set<TermDefinition> termDefinitions) {
		this.termDefinitions = termDefinitions;
	}

}