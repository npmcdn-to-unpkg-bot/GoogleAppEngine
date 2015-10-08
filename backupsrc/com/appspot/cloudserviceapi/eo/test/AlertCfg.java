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

/**
 * AlertCfg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "alert_cfg", catalog = "eol2")
public class AlertCfg implements java.io.Serializable {

	// Fields

	private Long id;
	private StudentParentPair studentParentPairByParentId;
	private StudentParentPair studentParentPairByStudentId;
	private Integer alertType;
	private Integer alertTimeout;
	private Boolean emailBool;
	private Boolean textBool;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public AlertCfg() {
	}

	/** full constructor */
	public AlertCfg(StudentParentPair studentParentPairByParentId,
			StudentParentPair studentParentPairByStudentId, Integer alertType,
			Integer alertTimeout, Boolean emailBool, Boolean textBool,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy) {
		this.studentParentPairByParentId = studentParentPairByParentId;
		this.studentParentPairByStudentId = studentParentPairByStudentId;
		this.alertType = alertType;
		this.alertTimeout = alertTimeout;
		this.emailBool = emailBool;
		this.textBool = textBool;
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
	@JoinColumn(name = "PARENT_ID", nullable = false)
	public StudentParentPair getStudentParentPairByParentId() {
		return this.studentParentPairByParentId;
	}

	public void setStudentParentPairByParentId(
			StudentParentPair studentParentPairByParentId) {
		this.studentParentPairByParentId = studentParentPairByParentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	public StudentParentPair getStudentParentPairByStudentId() {
		return this.studentParentPairByStudentId;
	}

	public void setStudentParentPairByStudentId(
			StudentParentPair studentParentPairByStudentId) {
		this.studentParentPairByStudentId = studentParentPairByStudentId;
	}

	@Column(name = "ALERT_TYPE", nullable = false)
	public Integer getAlertType() {
		return this.alertType;
	}

	public void setAlertType(Integer alertType) {
		this.alertType = alertType;
	}

	@Column(name = "ALERT_TIMEOUT", nullable = false)
	public Integer getAlertTimeout() {
		return this.alertTimeout;
	}

	public void setAlertTimeout(Integer alertTimeout) {
		this.alertTimeout = alertTimeout;
	}

	@Column(name = "EMAIL_BOOL", nullable = false)
	public Boolean getEmailBool() {
		return this.emailBool;
	}

	public void setEmailBool(Boolean emailBool) {
		this.emailBool = emailBool;
	}

	@Column(name = "TEXT_BOOL", nullable = false)
	public Boolean getTextBool() {
		return this.textBool;
	}

	public void setTextBool(Boolean textBool) {
		this.textBool = textBool;
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