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
import javax.persistence.UniqueConstraint;

/**
 * InksFeedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_feedback", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "FEEDBACK_ID" }))
public class InksFeedback implements java.io.Serializable {

	// Fields

	private Long id;
	private InksNode inksNode;
	private Mistake mistake;
	private Feedback feedback;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public InksFeedback() {
	}

	/** minimal constructor */
	public InksFeedback(InksNode inksNode, Mistake mistake, Feedback feedback) {
		this.inksNode = inksNode;
		this.mistake = mistake;
		this.feedback = feedback;
	}

	/** full constructor */
	public InksFeedback(InksNode inksNode, Mistake mistake, Feedback feedback,
			Integer sortOrder) {
		this.inksNode = inksNode;
		this.mistake = mistake;
		this.feedback = feedback;
		this.sortOrder = sortOrder;
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
	@JoinColumn(name = "INKS_NODE_ID", nullable = false)
	public InksNode getInksNode() {
		return this.inksNode;
	}

	public void setInksNode(InksNode inksNode) {
		this.inksNode = inksNode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MISTAKE_ID", nullable = false)
	public Mistake getMistake() {
		return this.mistake;
	}

	public void setMistake(Mistake mistake) {
		this.mistake = mistake;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FEEDBACK_ID", nullable = false)
	public Feedback getFeedback() {
		return this.feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}