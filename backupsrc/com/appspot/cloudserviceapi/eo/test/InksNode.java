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
 * InksNode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_node", catalog = "eol2")
public class InksNode implements java.io.Serializable {

	// Fields

	private Long id;
	private CdNodeType cdNodeType;
	private String name;
	private String implClassName;
	private Set<InksQuery> inksQueries = new HashSet<InksQuery>(0);
	private Set<InksNodeExpectedEvent> inksNodeExpectedEvents = new HashSet<InksNodeExpectedEvent>(
			0);
	private Set<InksNodeConcept> inksNodeConcepts = new HashSet<InksNodeConcept>(
			0);
	private Set<InksHint> inksHints = new HashSet<InksHint>(0);
	private Set<InksTree> inksTrees = new HashSet<InksTree>(0);
	private Set<InksNodeGoal> inksNodeGoals = new HashSet<InksNodeGoal>(0);
	private Set<InksNodeErrorReason> inksNodeErrorReasons = new HashSet<InksNodeErrorReason>(
			0);
	private Set<InksFeedback> inksFeedbacks = new HashSet<InksFeedback>(0);
	private Set<InksNodeRelation> inksNodeRelationsForInksChildNodeId = new HashSet<InksNodeRelation>(
			0);
	private Set<InksNodeRelation> inksNodeRelationsForInksNodeId = new HashSet<InksNodeRelation>(
			0);
	private Set<InksNodeEntry> inksNodeEntries = new HashSet<InksNodeEntry>(0);

	// Constructors

	/** default constructor */
	public InksNode() {
	}

	/** minimal constructor */
	public InksNode(CdNodeType cdNodeType, String name) {
		this.cdNodeType = cdNodeType;
		this.name = name;
	}

	/** full constructor */
	public InksNode(CdNodeType cdNodeType, String name, String implClassName,
			Set<InksQuery> inksQueries,
			Set<InksNodeExpectedEvent> inksNodeExpectedEvents,
			Set<InksNodeConcept> inksNodeConcepts, Set<InksHint> inksHints,
			Set<InksTree> inksTrees, Set<InksNodeGoal> inksNodeGoals,
			Set<InksNodeErrorReason> inksNodeErrorReasons,
			Set<InksFeedback> inksFeedbacks,
			Set<InksNodeRelation> inksNodeRelationsForInksChildNodeId,
			Set<InksNodeRelation> inksNodeRelationsForInksNodeId,
			Set<InksNodeEntry> inksNodeEntries) {
		this.cdNodeType = cdNodeType;
		this.name = name;
		this.implClassName = implClassName;
		this.inksQueries = inksQueries;
		this.inksNodeExpectedEvents = inksNodeExpectedEvents;
		this.inksNodeConcepts = inksNodeConcepts;
		this.inksHints = inksHints;
		this.inksTrees = inksTrees;
		this.inksNodeGoals = inksNodeGoals;
		this.inksNodeErrorReasons = inksNodeErrorReasons;
		this.inksFeedbacks = inksFeedbacks;
		this.inksNodeRelationsForInksChildNodeId = inksNodeRelationsForInksChildNodeId;
		this.inksNodeRelationsForInksNodeId = inksNodeRelationsForInksNodeId;
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
	@JoinColumn(name = "CD_NODE_TYPE_ID", nullable = false)
	public CdNodeType getCdNodeType() {
		return this.cdNodeType;
	}

	public void setCdNodeType(CdNodeType cdNodeType) {
		this.cdNodeType = cdNodeType;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IMPL_CLASS_NAME", length = 200)
	public String getImplClassName() {
		return this.implClassName;
	}

	public void setImplClassName(String implClassName) {
		this.implClassName = implClassName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNode")
	public Set<InksQuery> getInksQueries() {
		return this.inksQueries;
	}

	public void setInksQueries(Set<InksQuery> inksQueries) {
		this.inksQueries = inksQueries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inksNode")
	public Set<InksNodeExpectedEvent> getInksNodeExpectedEvents() {
		return this.inksNodeExpectedEvents;
	}

	public void setInksNodeExpectedEvents(
			Set<InksNodeExpectedEvent> inksNodeExpectedEvents) {
		this.inksNodeExpectedEvents = inksNodeExpectedEvents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNode")
	public Set<InksNodeConcept> getInksNodeConcepts() {
		return this.inksNodeConcepts;
	}

	public void setInksNodeConcepts(Set<InksNodeConcept> inksNodeConcepts) {
		this.inksNodeConcepts = inksNodeConcepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNode")
	public Set<InksHint> getInksHints() {
		return this.inksHints;
	}

	public void setInksHints(Set<InksHint> inksHints) {
		this.inksHints = inksHints;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNode")
	public Set<InksTree> getInksTrees() {
		return this.inksTrees;
	}

	public void setInksTrees(Set<InksTree> inksTrees) {
		this.inksTrees = inksTrees;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inksNode")
	public Set<InksNodeGoal> getInksNodeGoals() {
		return this.inksNodeGoals;
	}

	public void setInksNodeGoals(Set<InksNodeGoal> inksNodeGoals) {
		this.inksNodeGoals = inksNodeGoals;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inksNode")
	public Set<InksNodeErrorReason> getInksNodeErrorReasons() {
		return this.inksNodeErrorReasons;
	}

	public void setInksNodeErrorReasons(
			Set<InksNodeErrorReason> inksNodeErrorReasons) {
		this.inksNodeErrorReasons = inksNodeErrorReasons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNode")
	public Set<InksFeedback> getInksFeedbacks() {
		return this.inksFeedbacks;
	}

	public void setInksFeedbacks(Set<InksFeedback> inksFeedbacks) {
		this.inksFeedbacks = inksFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNodeByInksChildNodeId")
	public Set<InksNodeRelation> getInksNodeRelationsForInksChildNodeId() {
		return this.inksNodeRelationsForInksChildNodeId;
	}

	public void setInksNodeRelationsForInksChildNodeId(
			Set<InksNodeRelation> inksNodeRelationsForInksChildNodeId) {
		this.inksNodeRelationsForInksChildNodeId = inksNodeRelationsForInksChildNodeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksNodeByInksNodeId")
	public Set<InksNodeRelation> getInksNodeRelationsForInksNodeId() {
		return this.inksNodeRelationsForInksNodeId;
	}

	public void setInksNodeRelationsForInksNodeId(
			Set<InksNodeRelation> inksNodeRelationsForInksNodeId) {
		this.inksNodeRelationsForInksNodeId = inksNodeRelationsForInksNodeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "inksNode")
	public Set<InksNodeEntry> getInksNodeEntries() {
		return this.inksNodeEntries;
	}

	public void setInksNodeEntries(Set<InksNodeEntry> inksNodeEntries) {
		this.inksNodeEntries = inksNodeEntries;
	}

}