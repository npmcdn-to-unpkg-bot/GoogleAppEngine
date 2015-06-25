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
 * Feedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feedback", catalog = "eol2")
public class Feedback implements java.io.Serializable {

	// Fields

	private Long id;
	private CdContentType cdContentType;
	private String text;
	private String externalUrl;
	private Set<InksFeedback> inksFeedbacks = new HashSet<InksFeedback>(0);
	private Set<RatingFeedback> ratingFeedbacks = new HashSet<RatingFeedback>(0);

	// Constructors

	/** default constructor */
	public Feedback() {
	}

	/** minimal constructor */
	public Feedback(CdContentType cdContentType) {
		this.cdContentType = cdContentType;
	}

	/** full constructor */
	public Feedback(CdContentType cdContentType, String text,
			String externalUrl, Set<InksFeedback> inksFeedbacks,
			Set<RatingFeedback> ratingFeedbacks) {
		this.cdContentType = cdContentType;
		this.text = text;
		this.externalUrl = externalUrl;
		this.inksFeedbacks = inksFeedbacks;
		this.ratingFeedbacks = ratingFeedbacks;
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
	@JoinColumn(name = "CD_CONTENT_TYPE_ID", nullable = false)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "feedback")
	public Set<InksFeedback> getInksFeedbacks() {
		return this.inksFeedbacks;
	}

	public void setInksFeedbacks(Set<InksFeedback> inksFeedbacks) {
		this.inksFeedbacks = inksFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "feedback")
	public Set<RatingFeedback> getRatingFeedbacks() {
		return this.ratingFeedbacks;
	}

	public void setRatingFeedbacks(Set<RatingFeedback> ratingFeedbacks) {
		this.ratingFeedbacks = ratingFeedbacks;
	}

}