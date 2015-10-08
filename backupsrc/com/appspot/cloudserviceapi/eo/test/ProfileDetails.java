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
 * ProfileDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profile_details", catalog = "eol2")
public class ProfileDetails implements java.io.Serializable {

	// Fields

	private Long id;
	private Short sessnLngthMin;
	private Short sessnLngthMax;
	private String grade;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public ProfileDetails() {
	}

	/** full constructor */
	public ProfileDetails(Short sessnLngthMin, Short sessnLngthMax,
			String grade, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy) {
		this.sessnLngthMin = sessnLngthMin;
		this.sessnLngthMax = sessnLngthMax;
		this.grade = grade;
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

	@Column(name = "SESSN_LNGTH_MIN", nullable = false)
	public Short getSessnLngthMin() {
		return this.sessnLngthMin;
	}

	public void setSessnLngthMin(Short sessnLngthMin) {
		this.sessnLngthMin = sessnLngthMin;
	}

	@Column(name = "SESSN_LNGTH_MAX", nullable = false)
	public Short getSessnLngthMax() {
		return this.sessnLngthMax;
	}

	public void setSessnLngthMax(Short sessnLngthMax) {
		this.sessnLngthMax = sessnLngthMax;
	}

	@Column(name = "GRADE", nullable = false, length = 2)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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