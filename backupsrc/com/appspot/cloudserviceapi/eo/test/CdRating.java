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
 * CdRating entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_rating", catalog = "eol2")
public class CdRating implements java.io.Serializable {

	// Fields

	private Long id;
	private String ratingText;
	private Set<RatingFeedback> ratingFeedbacks = new HashSet<RatingFeedback>(0);
	private Set<RatingHint> ratingHints = new HashSet<RatingHint>(0);

	// Constructors

	/** default constructor */
	public CdRating() {
	}

	/** minimal constructor */
	public CdRating(String ratingText) {
		this.ratingText = ratingText;
	}

	/** full constructor */
	public CdRating(String ratingText, Set<RatingFeedback> ratingFeedbacks,
			Set<RatingHint> ratingHints) {
		this.ratingText = ratingText;
		this.ratingFeedbacks = ratingFeedbacks;
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

	@Column(name = "RATING_TEXT", nullable = false, length = 65535)
	public String getRatingText() {
		return this.ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdRating")
	public Set<RatingFeedback> getRatingFeedbacks() {
		return this.ratingFeedbacks;
	}

	public void setRatingFeedbacks(Set<RatingFeedback> ratingFeedbacks) {
		this.ratingFeedbacks = ratingFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdRating")
	public Set<RatingHint> getRatingHints() {
		return this.ratingHints;
	}

	public void setRatingHints(Set<RatingHint> ratingHints) {
		this.ratingHints = ratingHints;
	}

}