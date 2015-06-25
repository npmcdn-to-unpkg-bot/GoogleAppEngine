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
 * SecurityTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "security_ticket", catalog = "eol2")
public class SecurityTicket implements java.io.Serializable {

	// Fields

	private Long id;
	private User user;
	private CdTicketType cdTicketType;
	private String ticketCd;
	private String userName;
	private Long useLimit;
	private String status;
	private Date expirationDt;
	private String parentToken;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public SecurityTicket() {
	}

	/** minimal constructor */
	public SecurityTicket(User user, CdTicketType cdTicketType,
			String ticketCd, String userName, Long useLimit, String status,
			Date expirationDt, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy) {
		this.user = user;
		this.cdTicketType = cdTicketType;
		this.ticketCd = ticketCd;
		this.userName = userName;
		this.useLimit = useLimit;
		this.status = status;
		this.expirationDt = expirationDt;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public SecurityTicket(User user, CdTicketType cdTicketType,
			String ticketCd, String userName, Long useLimit, String status,
			Date expirationDt, String parentToken, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.user = user;
		this.cdTicketType = cdTicketType;
		this.ticketCd = ticketCd;
		this.userName = userName;
		this.useLimit = useLimit;
		this.status = status;
		this.expirationDt = expirationDt;
		this.parentToken = parentToken;
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
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_TICKET_TYPE_ID", nullable = false)
	public CdTicketType getCdTicketType() {
		return this.cdTicketType;
	}

	public void setCdTicketType(CdTicketType cdTicketType) {
		this.cdTicketType = cdTicketType;
	}

	@Column(name = "TICKET_CD", nullable = false, length = 100)
	public String getTicketCd() {
		return this.ticketCd;
	}

	public void setTicketCd(String ticketCd) {
		this.ticketCd = ticketCd;
	}

	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USE_LIMIT", nullable = false)
	public Long getUseLimit() {
		return this.useLimit;
	}

	public void setUseLimit(Long useLimit) {
		this.useLimit = useLimit;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DT", nullable = false, length = 10)
	public Date getExpirationDt() {
		return this.expirationDt;
	}

	public void setExpirationDt(Date expirationDt) {
		this.expirationDt = expirationDt;
	}

	@Column(name = "PARENT_TOKEN", length = 100)
	public String getParentToken() {
		return this.parentToken;
	}

	public void setParentToken(String parentToken) {
		this.parentToken = parentToken;
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