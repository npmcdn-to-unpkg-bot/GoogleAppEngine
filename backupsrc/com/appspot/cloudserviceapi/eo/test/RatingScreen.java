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
 * RatingScreen entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rating_screen", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"PROFILE_ID", "SCREEN_ID" }))
public class RatingScreen implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profile;
	private Screen screen;
	private Long profileId;
	private Double rating;
	private Date addedDt;
	private Long addedBy;

	// Constructors

	/** default constructor */
	public RatingScreen() {
	}

	/** minimal constructor */
	public RatingScreen(Profile profile, Screen screen, Double rating,
			Date addedDt, Long addedBy) {
		this.profile = profile;
		this.screen = screen;
		this.rating = rating;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
	}

	/** full constructor */
	public RatingScreen(Profile profile, Screen screen, Long profileId,
			Double rating, Date addedDt, Long addedBy) {
		this.profile = profile;
		this.screen = screen;
		this.profileId = profileId;
		this.rating = rating;
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
	@JoinColumn(name = "ID", unique = true, nullable = false, insertable = false, updatable = false)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCREEN_ID", nullable = false)
	public Screen getScreen() {
		return this.screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	@Column(name = "PROFILE_ID")
	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Column(name = "RATING", nullable = false, precision = 22, scale = 0)
	public Double getRating() {
		return this.rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
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