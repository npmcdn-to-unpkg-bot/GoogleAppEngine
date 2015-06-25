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
 * RoleOrgPair entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_org_pair", catalog = "eol2")
public class RoleOrgPair implements java.io.Serializable {

	// Fields

	private Long id;
	private Role role;
	private Organization organization;
	private Long maxMembers;
	private Set<KeyAssociation> keyAssociations = new HashSet<KeyAssociation>(0);
	private Set<Member> members = new HashSet<Member>(0);

	// Constructors

	/** default constructor */
	public RoleOrgPair() {
	}

	/** minimal constructor */
	public RoleOrgPair(Role role, Organization organization, Long maxMembers) {
		this.role = role;
		this.organization = organization;
		this.maxMembers = maxMembers;
	}

	/** full constructor */
	public RoleOrgPair(Role role, Organization organization, Long maxMembers,
			Set<KeyAssociation> keyAssociations, Set<Member> members) {
		this.role = role;
		this.organization = organization;
		this.maxMembers = maxMembers;
		this.keyAssociations = keyAssociations;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name = "MAX_MEMBERS", nullable = false)
	public Long getMaxMembers() {
		return this.maxMembers;
	}

	public void setMaxMembers(Long maxMembers) {
		this.maxMembers = maxMembers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleOrgPair")
	public Set<KeyAssociation> getKeyAssociations() {
		return this.keyAssociations;
	}

	public void setKeyAssociations(Set<KeyAssociation> keyAssociations) {
		this.keyAssociations = keyAssociations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleOrgPair")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

}