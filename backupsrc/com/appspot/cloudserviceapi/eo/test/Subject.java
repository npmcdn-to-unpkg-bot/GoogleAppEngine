package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.tapestry5.beaneditor.Validate;

import com.google.appengine.api.datastore.Key;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "subject", catalog = "eol2")
public class Subject implements java.io.Serializable {

	// Fields

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    //private Long id;	//can't be Long due to GAE/J JPA child limitation (error: Cannot have a java.lang.Long primary key and be a child object)
    private Key id;
	private String name;
	private Date updatedDt;
	private Long updatedBy;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProgramGoalsSchedule> programGoalsSchedules = new HashSet<ProgramGoalsSchedule>(
			0);

	// Constructors

/*	*//** default constructor *//*
	public Subject() {
	}

	*//** minimal constructor *//*
	public Subject(Date updatedDt, Long updatedBy) {
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor 
	public Subject(String name, Date updatedDt, Long updatedBy,
			Set<ProgramGoalsSchedule> programGoalsSchedules) {
		this.name = name;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.programGoalsSchedules = programGoalsSchedules;
	}

	// Property accessors
/*
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
*/
	
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	@Validate(value = "required")
	@NotNull
	public void setName(String name) {
		this.name = name;
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

	public Set<ProgramGoalsSchedule> getProgramGoalsSchedules() {
		return this.programGoalsSchedules;
	}

	public void setProgramGoalsSchedules(
			Set<ProgramGoalsSchedule> programGoalsSchedules) {
		this.programGoalsSchedules = programGoalsSchedules;
	}

}