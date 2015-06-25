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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "eol2")
public class User implements java.io.Serializable {

	// Fields

	private Long id;
	private Long profileId;
	private String userType;
	private String userName;
	private String password;
	private String passwordSalt;
	private Long cdHashMethodId;
	private Long cdStatusId;
	private Date passwordExpireDt;
	private Date lockExpireDt;
	private Long cdSecurityQuestionId;
	private String securityQuestionAnswer;
	private String emailAddress;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<SecurityTicket> securityTickets = new HashSet<SecurityTicket>(0);
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userType, String userName, String password,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy) {
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public User(Long profileId, String userType, String userName,
			String password, String passwordSalt, Long cdHashMethodId,
			Long cdStatusId, Date passwordExpireDt, Date lockExpireDt,
			Long cdSecurityQuestionId, String securityQuestionAnswer,
			String emailAddress, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy, Set<SecurityTicket> securityTickets,
			Set<Member> members) {
		this.profileId = profileId;
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.cdHashMethodId = cdHashMethodId;
		this.cdStatusId = cdStatusId;
		this.passwordExpireDt = passwordExpireDt;
		this.lockExpireDt = lockExpireDt;
		this.cdSecurityQuestionId = cdSecurityQuestionId;
		this.securityQuestionAnswer = securityQuestionAnswer;
		this.emailAddress = emailAddress;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.securityTickets = securityTickets;
		this.members = members;
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

	@Column(name = "USER_TYPE", nullable = false, length = 1)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PASSWORD_SALT", length = 6)
	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	@Column(name = "CD_HASH_METHOD_ID")
	public Long getCdHashMethodId() {
		return this.cdHashMethodId;
	}

	public void setCdHashMethodId(Long cdHashMethodId) {
		this.cdHashMethodId = cdHashMethodId;
	}

	@Column(name = "CD_STATUS_ID")
	public Long getCdStatusId() {
		return this.cdStatusId;
	}

	public void setCdStatusId(Long cdStatusId) {
		this.cdStatusId = cdStatusId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PASSWORD_EXPIRE_DT", length = 10)
	public Date getPasswordExpireDt() {
		return this.passwordExpireDt;
	}

	public void setPasswordExpireDt(Date passwordExpireDt) {
		this.passwordExpireDt = passwordExpireDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOCK_EXPIRE_DT", length = 10)
	public Date getLockExpireDt() {
		return this.lockExpireDt;
	}

	public void setLockExpireDt(Date lockExpireDt) {
		this.lockExpireDt = lockExpireDt;
	}

	@Column(name = "CD_SECURITY_QUESTION_ID")
	public Long getCdSecurityQuestionId() {
		return this.cdSecurityQuestionId;
	}

	public void setCdSecurityQuestionId(Long cdSecurityQuestionId) {
		this.cdSecurityQuestionId = cdSecurityQuestionId;
	}

	@Column(name = "SECURITY_QUESTION_ANSWER", length = 50)
	public String getSecurityQuestionAnswer() {
		return this.securityQuestionAnswer;
	}

	public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
		this.securityQuestionAnswer = securityQuestionAnswer;
	}

	@Column(name = "EMAIL_ADDRESS", length = 50)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<SecurityTicket> getSecurityTickets() {
		return this.securityTickets;
	}

	public void setSecurityTickets(Set<SecurityTicket> securityTickets) {
		this.securityTickets = securityTickets;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}