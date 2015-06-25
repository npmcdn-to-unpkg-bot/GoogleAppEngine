package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * StudentLearningStyle entity. @author MyEclipse Persistence Tools
 */
@Entity

@Table(name = "student_learning_style", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"PROFILE_ID", "LEARNING_STYLE_ID" }))
@SqlResultSetMapping(name="student_learning_style", entities=@EntityResult(entityClass=StudentLearningStyle.class))
@NamedNativeQuery(name="sls_with_profileId", query="select sls.id, sls.learning_style_id, sls.added_dt, sls.added_by, sls.updated_dt, sls.updated_by, sls.profile_id, sls.learning_style_type, sls.rating from student_learning_style sls where sls.profile_id= :profileId", resultSetMapping="student_learning_style")
		
public class StudentLearningStyle implements java.io.Serializable {

	// Fields

	private Long id;
	private CdLearningStyle cdLearningStyle;
	private Profile profile;
	private String learningStyleType;
	private Double rating;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public StudentLearningStyle() {
	}

	/** minimal constructor */
	public StudentLearningStyle(CdLearningStyle cdLearningStyle, Date addedDt,
			Long addedBy, Date updatedDt, Long updatedBy) {
		this.cdLearningStyle = cdLearningStyle;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public StudentLearningStyle(CdLearningStyle cdLearningStyle,
			Profile profile, String learningStyleType, Double rating,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy) {
		this.cdLearningStyle = cdLearningStyle;
		this.profile = profile;
		this.learningStyleType = learningStyleType;
		this.rating = rating;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
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
	@JoinColumn(name = "LEARNING_STYLE_ID", nullable = false)
	public CdLearningStyle getCdLearningStyle() {
		return this.cdLearningStyle;
	}

	public void setCdLearningStyle(CdLearningStyle cdLearningStyle) {
		this.cdLearningStyle = cdLearningStyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_ID")
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Column(name = "LEARNING_STYLE_TYPE", length = 1)
	public String getLearningStyleType() {
		return this.learningStyleType;
	}

	public void setLearningStyleType(String learningStyleType) {
		this.learningStyleType = learningStyleType;
	}

	@Column(name = "RATING", precision = 22, scale = 0)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DT", nullable = false, length = 10)
	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@Column(name = "UPDATED_BY", nullable = false)
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}