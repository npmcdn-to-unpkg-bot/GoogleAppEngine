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
 * Member entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"ROLE_ORG_PAIR_ID", "USER_ID", "START_DT" }))
public class Member implements java.io.Serializable {

	// Fields

	private Long id;
	private User user;
	private RoleOrgPair roleOrgPair;
	private Date startDt;
	private String status;
	private Date stopDt;
	private String usedKey;
	private Long addedBy;
	private Date addedDt;
	private Long updatedBy;
	private Date updateDt;

	// Constructors

	/** default constructor */
	public Member() {
	}

	/** minimal constructor */
	public Member(User user, RoleOrgPair roleOrgPair, Date startDt,
			String status, Long addedBy, Date addedDt, Long updatedBy,
			Date updateDt) {
		this.user = user;
		this.roleOrgPair = roleOrgPair;
		this.startDt = startDt;
		this.status = status;
		this.addedBy = addedBy;
		this.addedDt = addedDt;
		this.updatedBy = updatedBy;
		this.updateDt = updateDt;
	}

	/** full constructor */
	public Member(User user, RoleOrgPair roleOrgPair, Date startDt,
			String status, Date stopDt, String usedKey, Long addedBy,
			Date addedDt, Long updatedBy, Date updateDt) {
		this.user = user;
		this.roleOrgPair = roleOrgPair;
		this.startDt = startDt;
		this.status = status;
		this.stopDt = stopDt;
		this.usedKey = usedKey;
		this.addedBy = addedBy;
		this.addedDt = addedDt;
		this.updatedBy = updatedBy;
		this.updateDt = updateDt;
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
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ORG_PAIR_ID", nullable = false)
	public RoleOrgPair getRoleOrgPair() {
		return this.roleOrgPair;
	}

	public void setRoleOrgPair(RoleOrgPair roleOrgPair) {
		this.roleOrgPair = roleOrgPair;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DT", nullable = false, length = 10)
	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STOP_DT", length = 10)
	public Date getStopDt() {
		return this.stopDt;
	}

	public void setStopDt(Date stopDt) {
		this.stopDt = stopDt;
	}

	@Column(name = "USED_KEY", length = 10)
	public String getUsedKey() {
		return this.usedKey;
	}

	public void setUsedKey(String usedKey) {
		this.usedKey = usedKey;
	}

	@Column(name = "ADDED_BY", nullable = false)
	public Long getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(Long addedBy) {
		this.addedBy = addedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", nullable = false, length = 10)
	public Date getAddedDt() {
		return this.addedDt;
	}

	public void setAddedDt(Date addedDt) {
		this.addedDt = addedDt;
	}

	@Column(name = "UPDATED_BY", nullable = false)
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DT", nullable = false, length = 10)
	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}