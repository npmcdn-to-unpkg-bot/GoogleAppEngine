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
 * KeyAssociation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "key_association", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"KEY_CD", "ROLE_ORG_PAIR_ID" }))
public class KeyAssociation implements java.io.Serializable {

	// Fields

	private Long id;
	private Key key;
	private RoleOrgPair roleOrgPair;
	private String status;
	private Date startDt;
	private Date stopDt;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public KeyAssociation() {
	}

	/** minimal constructor */
	public KeyAssociation(Key key, RoleOrgPair roleOrgPair, String status,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy) {
		this.key = key;
		this.roleOrgPair = roleOrgPair;
		this.status = status;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public KeyAssociation(Key key, RoleOrgPair roleOrgPair, String status,
			Date startDt, Date stopDt, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.key = key;
		this.roleOrgPair = roleOrgPair;
		this.status = status;
		this.startDt = startDt;
		this.stopDt = stopDt;
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
	@JoinColumn(name = "KEY_CD", nullable = false)
	public Key getKey() {
		return this.key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ORG_PAIR_ID", nullable = false)
	public RoleOrgPair getRoleOrgPair() {
		return this.roleOrgPair;
	}

	public void setRoleOrgPair(RoleOrgPair roleOrgPair) {
		this.roleOrgPair = roleOrgPair;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DT", length = 10)
	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STOP_DT", length = 10)
	public Date getStopDt() {
		return this.stopDt;
	}

	public void setStopDt(Date stopDt) {
		this.stopDt = stopDt;
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