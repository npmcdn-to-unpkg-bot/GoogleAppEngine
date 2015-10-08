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
 * Explanation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "explanation", catalog = "eol2")
public class Explanation implements java.io.Serializable {

	// Fields

	private Long id;
	private String text;
	private String externalUrl;
	private Long cdContentTypeId;
	private Set<Screen> screens = new HashSet<Screen>(0);

	// Constructors

	/** default constructor */
	public Explanation() {
	}

	/** minimal constructor */
	public Explanation(Long cdContentTypeId) {
		this.cdContentTypeId = cdContentTypeId;
	}

	/** full constructor */
	public Explanation(String text, String externalUrl, Long cdContentTypeId,
			Set<Screen> screens) {
		this.text = text;
		this.externalUrl = externalUrl;
		this.cdContentTypeId = cdContentTypeId;
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

	@Column(name = "CD_CONTENT_TYPE_ID", nullable = false)
	public Long getCdContentTypeId() {
		return this.cdContentTypeId;
	}

	public void setCdContentTypeId(Long cdContentTypeId) {
		this.cdContentTypeId = cdContentTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "explanation")
	public Set<Screen> getScreens() {
		return this.screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

}