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
 * InksHint entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_hint", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"INKS_NODE_ID", "HINT_ID", "CD_HINT_SCOPE_ID" }))
public class InksHint implements java.io.Serializable {

	// Fields

	private Long id;
	private CdHintScope cdHintScope;
	private InksNode inksNode;
	private Hint hint;

	// Constructors

	/** default constructor */
	public InksHint() {
	}

	/** full constructor */
	public InksHint(CdHintScope cdHintScope, InksNode inksNode, Hint hint) {
		this.cdHintScope = cdHintScope;
		this.inksNode = inksNode;
		this.hint = hint;
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
	@JoinColumn(name = "CD_HINT_SCOPE_ID", nullable = false)
	public CdHintScope getCdHintScope() {
		return this.cdHintScope;
	}

	public void setCdHintScope(CdHintScope cdHintScope) {
		this.cdHintScope = cdHintScope;
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
	@JoinColumn(name = "HINT_ID", nullable = false)
	public Hint getHint() {
		return this.hint;
	}

	public void setHint(Hint hint) {
		this.hint = hint;
	}

}