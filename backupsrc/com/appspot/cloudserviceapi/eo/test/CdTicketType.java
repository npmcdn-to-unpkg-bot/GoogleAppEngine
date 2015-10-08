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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CdTicketType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_ticket_type", catalog = "eol2")
public class CdTicketType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<SecurityTicket> securityTickets = new HashSet<SecurityTicket>(0);

	// Constructors

	/** default constructor */
	public CdTicketType() {
	}

	/** minimal constructor */
	public CdTicketType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdTicketType(String name, Set<SecurityTicket> securityTickets) {
		this.name = name;
		this.securityTickets = securityTickets;
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

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdTicketType")
	public Set<SecurityTicket> getSecurityTickets() {
		return this.securityTickets;
	}

	public void setSecurityTickets(Set<SecurityTicket> securityTickets) {
		this.securityTickets = securityTickets;
	}

}