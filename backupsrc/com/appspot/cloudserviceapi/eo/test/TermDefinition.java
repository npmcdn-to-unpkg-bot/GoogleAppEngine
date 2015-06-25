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
 * TermDefinition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "term_definition", catalog = "eol2")
public class TermDefinition implements java.io.Serializable {

	// Fields

	private Long id;
	private Goal goal;
	private String content;
	private String externalUrl;

	// Constructors

	/** default constructor */
	public TermDefinition() {
	}

	/** minimal constructor */
	public TermDefinition(Goal goal, String content) {
		this.goal = goal;
		this.content = content;
	}

	/** full constructor */
	public TermDefinition(Goal goal, String content, String externalUrl) {
		this.goal = goal;
		this.content = content;
		this.externalUrl = externalUrl;
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
	@JoinColumn(name = "GOAL_ID", nullable = false)
	public Goal getGoal() {
		return this.goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	@Column(name = "CONTENT", nullable = false, length = 250)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "EXTERNAL_URL", length = 250)
	public String getExternalUrl() {
		return this.externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

}