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
 * InksNodeErrorReason entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_node_error_reason", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "INKS_EVENT_ID", "INKS_MESSAGE_ID" }))
public class InksNodeErrorReason implements java.io.Serializable {

	// Fields

	private Long id;
	private InksNode inksNode;
	private InksMessage inksMessage;
	private InksEvent inksEvent;

	// Constructors

	/** default constructor */
	public InksNodeErrorReason() {
	}

	/** minimal constructor */
	public InksNodeErrorReason(Long id) {
		this.id = id;
	}

	/** full constructor */
	public InksNodeErrorReason(Long id, InksNode inksNode,
			InksMessage inksMessage, InksEvent inksEvent) {
		this.id = id;
		this.inksNode = inksNode;
		this.inksMessage = inksMessage;
		this.inksEvent = inksEvent;
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
	@JoinColumn(name = "INKS_MESSAGE_ID")
	public InksMessage getInksMessage() {
		return this.inksMessage;
	}

	public void setInksMessage(InksMessage inksMessage) {
		this.inksMessage = inksMessage;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "INKS_EVENT_ID")
	public InksEvent getInksEvent() {
		return this.inksEvent;
	}

	public void setInksEvent(InksEvent inksEvent) {
		this.inksEvent = inksEvent;
	}

}