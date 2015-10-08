package com.appspot.cloudserviceapi.eo.test;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AlertLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "alert_log", catalog = "eol2")
public class AlertLog implements java.io.Serializable {

	// Fields

	private Long id;
	private StudentParentPair studentParentPair;
	private CdAlertType cdAlertType;
	private CdSubject cdSubject;
	private Timestamp alertSentTs;
	private Timestamp alertAckdTs;
	private Timestamp alertDispTs;
	private Integer alertRetries;
	private Integer alertStatus;
	private Date alertExpirDt;
	private Date addedDt;
	private String addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public AlertLog() {
	}

	/** minimal constructor */
	public AlertLog(Long id, StudentParentPair studentParentPair,
			CdAlertType cdAlertType, CdSubject cdSubject,
			Timestamp alertSentTs, Integer alertRetries, Integer alertStatus,
			Date alertExpirDt, Date addedDt, String addedBy, Date updatedDt,
			Long updatedBy) {
		this.id = id;
		this.studentParentPair = studentParentPair;
		this.cdAlertType = cdAlertType;
		this.cdSubject = cdSubject;
		this.alertSentTs = alertSentTs;
		this.alertRetries = alertRetries;
		this.alertStatus = alertStatus;
		this.alertExpirDt = alertExpirDt;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public AlertLog(Long id, StudentParentPair studentParentPair,
			CdAlertType cdAlertType, CdSubject cdSubject,
			Timestamp alertSentTs, Timestamp alertAckdTs,
			Timestamp alertDispTs, Integer alertRetries, Integer alertStatus,
			Date alertExpirDt, Date addedDt, String addedBy, Date updatedDt,
			Long updatedBy) {
		this.id = id;
		this.studentParentPair = studentParentPair;
		this.cdAlertType = cdAlertType;
		this.cdSubject = cdSubject;
		this.alertSentTs = alertSentTs;
		this.alertAckdTs = alertAckdTs;
		this.alertDispTs = alertDispTs;
		this.alertRetries = alertRetries;
		this.alertStatus = alertStatus;
		this.alertExpirDt = alertExpirDt;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_PARENT_ID", nullable = false)
	public StudentParentPair getStudentParentPair() {
		return this.studentParentPair;
	}

	public void setStudentParentPair(StudentParentPair studentParentPair) {
		this.studentParentPair = studentParentPair;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ALERT_TYPE", nullable = false)
	public CdAlertType getCdAlertType() {
		return this.cdAlertType;
	}

	public void setCdAlertType(CdAlertType cdAlertType) {
		this.cdAlertType = cdAlertType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID", nullable = false)
	public CdSubject getCdSubject() {
		return this.cdSubject;
	}

	public void setCdSubject(CdSubject cdSubject) {
		this.cdSubject = cdSubject;
	}

	@Column(name = "ALERT_SENT_TS", nullable = false, length = 19)
	public Timestamp getAlertSentTs() {
		return this.alertSentTs;
	}

	public void setAlertSentTs(Timestamp alertSentTs) {
		this.alertSentTs = alertSentTs;
	}

	@Column(name = "ALERT_ACKD_TS", length = 19)
	public Timestamp getAlertAckdTs() {
		return this.alertAckdTs;
	}

	public void setAlertAckdTs(Timestamp alertAckdTs) {
		this.alertAckdTs = alertAckdTs;
	}

	@Column(name = "ALERT_DISP_TS", length = 19)
	public Timestamp getAlertDispTs() {
		return this.alertDispTs;
	}

	public void setAlertDispTs(Timestamp alertDispTs) {
		this.alertDispTs = alertDispTs;
	}

	@Column(name = "ALERT_RETRIES", nullable = false)
	public Integer getAlertRetries() {
		return this.alertRetries;
	}

	public void setAlertRetries(Integer alertRetries) {
		this.alertRetries = alertRetries;
	}

	@Column(name = "ALERT_STATUS", nullable = false)
	public Integer getAlertStatus() {
		return this.alertStatus;
	}

	public void setAlertStatus(Integer alertStatus) {
		this.alertStatus = alertStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ALERT_EXPIR_DT", nullable = false, length = 10)
	public Date getAlertExpirDt() {
		return this.alertExpirDt;
	}

	public void setAlertExpirDt(Date alertExpirDt) {
		this.alertExpirDt = alertExpirDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", nullable = false, length = 10)
	public Date getAddedDt() {
		return this.addedDt;
	}

	public void setAddedDt(Date addedDt) {
		this.addedDt = addedDt;
	}

	@Column(name = "ADDED_BY", nullable = false, length = 20)
	public String getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(String addedBy) {
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