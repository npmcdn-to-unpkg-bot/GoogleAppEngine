package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * RatingFeedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rating_feedback", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"PROFILE_ID", "FEEDBACK_ID", "ADDED_DT" }))
public class RatingFeedback implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profile;
	private CdRating cdRating;
	private Feedback feedback;
	private Date addedDt;
	private Long addedBy;

	// Constructors

	/** default constructor */
	public RatingFeedback() {
	}

	/** full constructor */
	public RatingFeedback(Profile profile, CdRating cdRating,
			Feedback feedback, Date addedDt, Long addedBy) {
		this.profile = profile;
		this.cdRating = cdRating;
		this.feedback = feedback;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
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
	@JoinColumn(name = "PROFILE_ID", nullable = false)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_RATING_ID", nullable = false)
	public CdRating getCdRating() {
		return this.cdRating;
	}

	public void setCdRating(CdRating cdRating) {
		this.cdRating = cdRating;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FEEDBACK_ID", nullable = false)
	public Feedback getFeedback() {
		return this.feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", nullable = false, length = 10)
	public Date getAddedDt() {
		return this.addedDt;
	}

	public void setAddedDt(Date addedDt) {
		this.addedDt = addedDt;
	}

	@Column(name = "ADDED_BY", nullable = false)
	public Long getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(Long addedBy) {
		this.addedBy = addedBy;
	}

}