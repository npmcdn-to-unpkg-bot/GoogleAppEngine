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
 * Hint entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hint", catalog = "eol2")
public class Hint implements java.io.Serializable {

	// Fields

	private Long id;
	private CdContentType cdContentType;
	private String text;
	private String externalUrl;
	private Set<InksHint> inksHints = new HashSet<InksHint>(0);
	private Set<RatingHint> ratingHints = new HashSet<RatingHint>(0);

	// Constructors

	/** default constructor */
	public Hint() {
	}

	/** full constructor */
	public Hint(CdContentType cdContentType, String text, String externalUrl,
			Set<InksHint> inksHints, Set<RatingHint> ratingHints) {
		this.cdContentType = cdContentType;
		this.text = text;
		this.externalUrl = externalUrl;
		this.inksHints = inksHints;
		this.ratingHints = ratingHints;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hint")
	public Set<InksHint> getInksHints() {
		return this.inksHints;
	}

	public void setInksHints(Set<InksHint> inksHints) {
		this.inksHints = inksHints;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hint")
	public Set<RatingHint> getRatingHints() {
		return this.ratingHints;
	}

	public void setRatingHints(Set<RatingHint> ratingHints) {
		this.ratingHints = ratingHints;
	}

}