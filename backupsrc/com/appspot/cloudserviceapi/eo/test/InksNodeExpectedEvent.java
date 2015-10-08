package com.appspot.cloudserviceapi.eo.test;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * InksNodeExpectedEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_node_expected_event", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "INKS_EVENT_ID" }))
public class InksNodeExpectedEvent implements java.io.Serializable {

	// Fields

	private Long id;
	private InksNode inksNode;
	private InksEvent inksEvent;
	private String doAction;

	// Constructors

	/** default constructor */
	public InksNodeExpectedEvent() {
	}

	/** minimal constructor */
	public InksNodeExpectedEvent(Long id) {
		this.id = id;
	}

	/** full constructor */
	public InksNodeExpectedEvent(Long id, InksNode inksNode,
			InksEvent inksEvent, String doAction) {
		this.id = id;
		this.inksNode = inksNode;
		this.inksEvent = inksEvent;
		this.doAction = doAction;
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
	@JoinColumn(name = "INKS_NODE_ID")
	public InksNode getInksNode() {
		return this.inksNode;
	}

	public void setInksNode(InksNode inksNode) {
		this.inksNode = inksNode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "INKS_EVENT_ID")
	public InksEvent getInksEvent() {
		return this.inksEvent;
	}

	public void setInksEvent(InksEvent inksEvent) {
		this.inksEvent = inksEvent;
	}

	@Column(name = "DO_ACTION", length = 200)
	public String getDoAction() {
		return this.doAction;
	}

	public void setDoAction(String doAction) {
		this.doAction = doAction;
	}

}