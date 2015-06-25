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
 * Key entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "key", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = "KEY_CD"))
public class Key implements java.io.Serializable {

	// Fields

	private Long id;
	private CdKeyType cdKeyType;
	private String keyCd;
	private String status;
	private Date startDt;
	private Date stopDt;
	private Long useLimit;
	private Integer createBy;
	private Date addedDt;
	private Integer updateBy;
	private Date updateDt;
	private Set<KeyAssociation> keyAssociations = new HashSet<KeyAssociation>(0);

	// Constructors

	/** default constructor */
	public Key() {
	}

	/** minimal constructor */
	public Key(CdKeyType cdKeyType, String keyCd, String status, Date startDt,
			Date stopDt, Integer createBy, Date addedDt, Integer updateBy,
			Date updateDt) {
		this.cdKeyType = cdKeyType;
		this.keyCd = keyCd;
		this.status = status;
		this.startDt = startDt;
		this.stopDt = stopDt;
		this.createBy = createBy;
		this.addedDt = addedDt;
		this.updateBy = updateBy;
		this.updateDt = updateDt;
	}

	/** full constructor */
	public Key(CdKeyType cdKeyType, String keyCd, String status, Date startDt,
			Date stopDt, Long useLimit, Integer createBy, Date addedDt,
			Integer updateBy, Date updateDt, Set<KeyAssociation> keyAssociations) {
		this.cdKeyType = cdKeyType;
		this.keyCd = keyCd;
		this.status = status;
		this.startDt = startDt;
		this.stopDt = stopDt;
		this.useLimit = useLimit;
		this.createBy = createBy;
		this.addedDt = addedDt;
		this.updateBy = updateBy;
		this.updateDt = updateDt;
		this.keyAssociations = keyAssociations;
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
	@JoinColumn(name = "CD_KEY_TYPE_ID", nullable = false)
	public CdKeyType getCdKeyType() {
		return this.cdKeyType;
	}

	public void setCdKeyType(CdKeyType cdKeyType) {
		this.cdKeyType = cdKeyType;
	}

	@Column(name = "KEY_CD", unique = true, nullable = false, length = 10)
	public String getKeyCd() {
		return this.keyCd;
	}

	public void setKeyCd(String keyCd) {
		this.keyCd = keyCd;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DT", nullable = false, length = 10)
	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STOP_DT", nullable = false, length = 10)
	public Date getStopDt() {
		return this.stopDt;
	}

	public void setStopDt(Date stopDt) {
		this.stopDt = stopDt;
	}

	@Column(name = "USE_LIMIT")
	public Long getUseLimit() {
		return this.useLimit;
	}

	public void setUseLimit(Long useLimit) {
		this.useLimit = useLimit;
	}

	@Column(name = "CREATE_BY", nullable = false)
	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", nullable = false, length = 10)
	public Date getAddedDt() {
		return this.addedDt;
	}

	public void setAddedDt(Date addedDt) {
		this.addedDt = addedDt;
	}

	@Column(name = "UPDATE_BY", nullable = false)
	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DT", nullable = false, length = 10)
	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "key")
	public Set<KeyAssociation> getKeyAssociations() {
		return this.keyAssociations;
	}

	public void setKeyAssociations(Set<KeyAssociation> keyAssociations) {
		this.keyAssociations = keyAssociations;
	}

}