package com.appspot.cloudserviceapi.eo.test;

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

/**
 * Organization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "organization", catalog = "eol2")
public class Organization implements java.io.Serializable {

	// Fields

	private Long id;
	private CdOrgType cdOrgType;
	private CdStatus cdStatus;
	private CdOrgScope cdOrgScope;
	private String name;
	private String fullName;
	private Integer sortOrder;
	private Set<StudentSchoolPair> studentSchoolPairs = new HashSet<StudentSchoolPair>(
			0);
	private Set<RoleOrgPair> roleOrgPairs = new HashSet<RoleOrgPair>(0);

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(CdOrgType cdOrgType, CdStatus cdStatus,
			CdOrgScope cdOrgScope, String name) {
		this.cdOrgType = cdOrgType;
		this.cdStatus = cdStatus;
		this.cdOrgScope = cdOrgScope;
		this.name = name;
	}

	/** full constructor */
	public Organization(CdOrgType cdOrgType, CdStatus cdStatus,
			CdOrgScope cdOrgScope, String name, String fullName,
			Integer sortOrder, Set<StudentSchoolPair> studentSchoolPairs,
			Set<RoleOrgPair> roleOrgPairs) {
		this.cdOrgType = cdOrgType;
		this.cdStatus = cdStatus;
		this.cdOrgScope = cdOrgScope;
		this.name = name;
		this.fullName = fullName;
		this.sortOrder = sortOrder;
		this.studentSchoolPairs = studentSchoolPairs;
		this.roleOrgPairs = roleOrgPairs;
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
	@JoinColumn(name = "CD_ORG_TYPE_ID", nullable = false)
	public CdOrgType getCdOrgType() {
		return this.cdOrgType;
	}

	public void setCdOrgType(CdOrgType cdOrgType) {
		this.cdOrgType = cdOrgType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_STATUS_ID", nullable = false)
	public CdStatus getCdStatus() {
		return this.cdStatus;
	}

	public void setCdStatus(CdStatus cdStatus) {
		this.cdStatus = cdStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ORG_SCOPE_ID", nullable = false)
	public CdOrgScope getCdOrgScope() {
		return this.cdOrgScope;
	}

	public void setCdOrgScope(CdOrgScope cdOrgScope) {
		this.cdOrgScope = cdOrgScope;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "FULL_NAME", length = 200)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<StudentSchoolPair> getStudentSchoolPairs() {
		return this.studentSchoolPairs;
	}

	public void setStudentSchoolPairs(Set<StudentSchoolPair> studentSchoolPairs) {
		this.studentSchoolPairs = studentSchoolPairs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<RoleOrgPair> getRoleOrgPairs() {
		return this.roleOrgPairs;
	}

	public void setRoleOrgPairs(Set<RoleOrgPair> roleOrgPairs) {
		this.roleOrgPairs = roleOrgPairs;
	}

}