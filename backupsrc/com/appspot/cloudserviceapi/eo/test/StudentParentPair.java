package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * StudentParentPair entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_parent_pair", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"STUDENT_ID", "PARENT_ID" }))
public class StudentParentPair implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profileByParentId;
	private Profile profileByStudentId;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<AlertCfg> alertCfgsForStudentId = new HashSet<AlertCfg>(0);
	private Set<AlertCfg> alertCfgsForParentId = new HashSet<AlertCfg>(0);
	private Set<AlertLog> alertLogs = new HashSet<AlertLog>(0);

	// Constructors

	/** default constructor */
	public StudentParentPair() {
	}

	/** minimal constructor */
	public StudentParentPair(Profile profileByParentId,
			Profile profileByStudentId, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.profileByParentId = profileByParentId;
		this.profileByStudentId = profileByStudentId;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public StudentParentPair(Profile profileByParentId,
			Profile profileByStudentId, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy,
			Set<AlertCfg> alertCfgsForStudentId,
			Set<AlertCfg> alertCfgsForParentId, Set<AlertLog> alertLogs) {
		this.profileByParentId = profileByParentId;
		this.profileByStudentId = profileByStudentId;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.alertCfgsForStudentId = alertCfgsForStudentId;
		this.alertCfgsForParentId = alertCfgsForParentId;
		this.alertLogs = alertLogs;
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
	@JoinColumn(name = "PARENT_ID", nullable = false)
	public Profile getProfileByParentId() {
		return this.profileByParentId;
	}

	public void setProfileByParentId(Profile profileByParentId) {
		this.profileByParentId = profileByParentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	public Profile getProfileByStudentId() {
		return this.profileByStudentId;
	}

	public void setProfileByStudentId(Profile profileByStudentId) {
		this.profileByStudentId = profileByStudentId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studentParentPairByStudentId")
	public Set<AlertCfg> getAlertCfgsForStudentId() {
		return this.alertCfgsForStudentId;
	}

	public void setAlertCfgsForStudentId(Set<AlertCfg> alertCfgsForStudentId) {
		this.alertCfgsForStudentId = alertCfgsForStudentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studentParentPairByParentId")
	public Set<AlertCfg> getAlertCfgsForParentId() {
		return this.alertCfgsForParentId;
	}

	public void setAlertCfgsForParentId(Set<AlertCfg> alertCfgsForParentId) {
		this.alertCfgsForParentId = alertCfgsForParentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studentParentPair")
	public Set<AlertLog> getAlertLogs() {
		return this.alertLogs;
	}

	public void setAlertLogs(Set<AlertLog> alertLogs) {
		this.alertLogs = alertLogs;
	}

}