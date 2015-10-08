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
import javax.persistence.UniqueConstraint;

/**
 * InksTree entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_tree", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class InksTree implements java.io.Serializable {

	// Fields

	private Long id;
	private InksNode inksNode;
	private String name;
	private String description;
	private Set<Screen> screens = new HashSet<Screen>(0);

	// Constructors

	/** default constructor */
	public InksTree() {
	}

	/** minimal constructor */
	public InksTree(InksNode inksNode, String name) {
		this.inksNode = inksNode;
		this.name = name;
	}

	/** full constructor */
	public InksTree(InksNode inksNode, String name, String description,
			Set<Screen> screens) {
		this.inksNode = inksNode;
		this.name = name;
		this.description = description;
		this.screens = screens;
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
	@JoinColumn(name = "INKS_ROOT_NODE_ID", nullable = false)
	public InksNode getInksNode() {
		return this.inksNode;
	}

	public void setInksNode(InksNode inksNode) {
		this.inksNode = inksNode;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksTree")
	public Set<Screen> getScreens() {
		return this.screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

}