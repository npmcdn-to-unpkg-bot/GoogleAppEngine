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
 * Instruction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "instruction", catalog = "eol2")
public class Instruction implements java.io.Serializable {

	// Fields

	private Long id;
	private CdContentType cdContentType;
	private String text;
	private String externalUrl;
	private Set<Screen> screens = new HashSet<Screen>(0);

	// Constructors

	/** default constructor */
	public Instruction() {
	}

	/** full constructor */
	public Instruction(CdContentType cdContentType, String text,
			String externalUrl, Set<Screen> screens) {
		this.cdContentType = cdContentType;
		this.text = text;
		this.externalUrl = externalUrl;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_CONTENT_TYPE_ID")
	public CdContentType getCdContentType() {
		return this.cdContentType;
	}

	public void setCdContentType(CdContentType cdContentType) {
		this.cdContentType = cdContentType;
	}

	@Column(name = "TEXT", length = 500)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "EXTERNAL_URL", length = 500)
	public String getExternalUrl() {
		return this.externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instruction")
	public Set<Screen> getScreens() {
		return this.screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

}