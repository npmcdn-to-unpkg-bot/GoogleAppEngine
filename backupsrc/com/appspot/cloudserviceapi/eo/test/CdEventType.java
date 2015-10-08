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
 * CdEventType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_event_type", catalog = "eol2")
public class CdEventType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String description;
	private Set<InksEvent> inksEvents = new HashSet<InksEvent>(0);

	// Constructors

	/** default constructor */
	public CdEventType() {
	}

	/** minimal constructor */
	public CdEventType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdEventType(String name, String description,
			Set<InksEvent> inksEvents) {
		this.name = name;
		this.description = description;
		this.inksEvents = inksEvents;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdEventType")
	public Set<InksEvent> getInksEvents() {
		return this.inksEvents;
	}

	public void setInksEvents(Set<InksEvent> inksEvents) {
		this.inksEvents = inksEvents;
	}

}