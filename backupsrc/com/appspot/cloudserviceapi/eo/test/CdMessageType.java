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
 * CdMessageType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_message_type", catalog = "eol2")
public class CdMessageType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String description;
	private Set<InksMessage> inksMessages = new HashSet<InksMessage>(0);

	// Constructors

	/** default constructor */
	public CdMessageType() {
	}

	/** minimal constructor */
	public CdMessageType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdMessageType(String name, String description,
			Set<InksMessage> inksMessages) {
		this.name = name;
		this.description = description;
		this.inksMessages = inksMessages;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdMessageType")
	public Set<InksMessage> getInksMessages() {
		return this.inksMessages;
	}

	public void setInksMessages(Set<InksMessage> inksMessages) {
		this.inksMessages = inksMessages;
	}

}