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
 * Mistake entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mistake", catalog = "eol2")
public class Mistake implements java.io.Serializable {

	// Fields

	private Long id;
	private InksEvent inksEvent;
	private String name;
	private String description;
	private Integer sortOrder;
	private Long inksNodeId;
	private Set<InksQuery> inksQueries = new HashSet<InksQuery>(0);
	private Set<InksFeedback> inksFeedbacks = new HashSet<InksFeedback>(0);

	// Constructors

	/** default constructor */
	public Mistake() {
	}

	/** minimal constructor */
	public Mistake(String name) {
		this.name = name;
	}

	/** full constructor */
	public Mistake(InksEvent inksEvent, String name, String description,
			Integer sortOrder, Long inksNodeId, Set<InksQuery> inksQueries,
			Set<InksFeedback> inksFeedbacks) {
		this.inksEvent = inksEvent;
		this.name = name;
		this.description = description;
		this.sortOrder = sortOrder;
		this.inksNodeId = inksNodeId;
		this.inksQueries = inksQueries;
		this.inksFeedbacks = inksFeedbacks;
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
	@JoinColumn(name = "INKS_EVENT_ID")
	public InksEvent getInksEvent() {
		return this.inksEvent;
	}

	public void setInksEvent(InksEvent inksEvent) {
		this.inksEvent = inksEvent;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 250)
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

	@Column(name = "INKS_NODE_ID")
	public Long getInksNodeId() {
		return this.inksNodeId;
	}

	public void setInksNodeId(Long inksNodeId) {
		this.inksNodeId = inksNodeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mistake")
	public Set<InksQuery> getInksQueries() {
		return this.inksQueries;
	}

	public void setInksQueries(Set<InksQuery> inksQueries) {
		this.inksQueries = inksQueries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mistake")
	public Set<InksFeedback> getInksFeedbacks() {
		return this.inksFeedbacks;
	}

	public void setInksFeedbacks(Set<InksFeedback> inksFeedbacks) {
		this.inksFeedbacks = inksFeedbacks;
	}

}