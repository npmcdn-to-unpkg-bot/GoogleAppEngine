package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StudentLsActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_ls_activity", catalog = "eol2")
public class StudentLsActivity implements java.io.Serializable {

	// Fields

	private Long id;
	private Long profileId;
	private Long screenId;
	private String reason;
	private Date addedDt;
	private Long addedBy;

	// Constructors

	/** default constructor */
	public StudentLsActivity() {
	}

	/** minimal constructor */
	public StudentLsActivity(Date addedDt, Long addedBy) {
		this.addedDt = addedDt;
		this.addedBy = addedBy;
	}

	/** full constructor */
	public StudentLsActivity(Long profileId, Long screenId, String reason,
			Date addedDt, Long addedBy) {
		this.profileId = profileId;
		this.screenId = screenId;
		this.reason = reason;
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

	@Column(name = "PROFILE_ID")
	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Column(name = "SCREEN_ID")
	public Long getScreenId() {
		return this.screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	@Column(name = "REASON", length = 1)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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