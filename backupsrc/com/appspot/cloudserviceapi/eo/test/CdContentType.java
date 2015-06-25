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
 * CdContentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_content_type", catalog = "eol2")
public class CdContentType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer sortOrder;
	private Set<Query> queries = new HashSet<Query>(0);
	private Set<Instruction> instructions = new HashSet<Instruction>(0);
	private Set<Feedback> feedbacks = new HashSet<Feedback>(0);
	private Set<Hint> hints = new HashSet<Hint>(0);

	// Constructors

	/** default constructor */
	public CdContentType() {
	}

	/** minimal constructor */
	public CdContentType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdContentType(String name, Integer sortOrder, Set<Query> queries,
			Set<Instruction> instructions, Set<Feedback> feedbacks,
			Set<Hint> hints) {
		this.name = name;
		this.sortOrder = sortOrder;
		this.queries = queries;
		this.instructions = instructions;
		this.feedbacks = feedbacks;
		this.hints = hints;
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

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdContentType")
	public Set<Query> getQueries() {
		return this.queries;
	}

	public void setQueries(Set<Query> queries) {
		this.queries = queries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdContentType")
	public Set<Instruction> getInstructions() {
		return this.instructions;
	}

	public void setInstructions(Set<Instruction> instructions) {
		this.instructions = instructions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdContentType")
	public Set<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdContentType")
	public Set<Hint> getHints() {
		return this.hints;
	}

	public void setHints(Set<Hint> hints) {
		this.hints = hints;
	}

}