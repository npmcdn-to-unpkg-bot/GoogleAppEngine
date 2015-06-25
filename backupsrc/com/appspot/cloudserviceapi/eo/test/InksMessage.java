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
 * InksMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_message", catalog = "eol2")
public class InksMessage implements java.io.Serializable {

	// Fields

	private Long id;
	private CdMessageType cdMessageType;
	private String name;
	private String description;
	private Long topicId;
	private Set<InksNodeGoal> inksNodeGoals = new HashSet<InksNodeGoal>(0);
	private Set<InksNodeErrorReason> inksNodeErrorReasons = new HashSet<InksNodeErrorReason>(
			0);
	private Set<InksNodeEntry> inksNodeEntries = new HashSet<InksNodeEntry>(0);

	// Constructors

	/** default constructor */
	public InksMessage() {
	}

	/** minimal constructor */
	public InksMessage(CdMessageType cdMessageType, String name) {
		this.cdMessageType = cdMessageType;
		this.name = name;
	}

	/** full constructor */
	public InksMessage(CdMessageType cdMessageType, String name,
			String description, Long topicId, Set<InksNodeGoal> inksNodeGoals,
			Set<InksNodeErrorReason> inksNodeErrorReasons,
			Set<InksNodeEntry> inksNodeEntries) {
		this.cdMessageType = cdMessageType;
		this.name = name;
		this.description = description;
		this.topicId = topicId;
		this.inksNodeGoals = inksNodeGoals;
		this.inksNodeErrorReasons = inksNodeErrorReasons;
		this.inksNodeEntries = inksNodeEntries;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_MESSAGE_TYPE_ID", nullable = false)
	public CdMessageType getCdMessageType() {
		return this.cdMessageType;
	}

	public void setCdMessageType(CdMessageType cdMessageType) {
		this.cdMessageType = cdMessageType;
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

	@Column(name = "TOPIC_ID")
	public Long getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksMessage")
	public Set<InksNodeGoal> getInksNodeGoals() {
		return this.inksNodeGoals;
	}

	public void setInksNodeGoals(Set<InksNodeGoal> inksNodeGoals) {
		this.inksNodeGoals = inksNodeGoals;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksMessage")
	public Set<InksNodeErrorReason> getInksNodeErrorReasons() {
		return this.inksNodeErrorReasons;
	}

	public void setInksNodeErrorReasons(
			Set<InksNodeErrorReason> inksNodeErrorReasons) {
		this.inksNodeErrorReasons = inksNodeErrorReasons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksMessage")
	public Set<InksNodeEntry> getInksNodeEntries() {
		return this.inksNodeEntries;
	}

	public void setInksNodeEntries(Set<InksNodeEntry> inksNodeEntries) {
		this.inksNodeEntries = inksNodeEntries;
	}

}