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
 * CdCollegeType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_college_type", catalog = "eol2")
public class CdCollegeType implements java.io.Serializable {

	// Fields

	private Long id;
	private String description;
	private Double collegeGpa;
	private Integer collegeSat;
	private Integer collegeAct;
	private Set<ProgramGoalsParentTarget> programGoalsParentTargets = new HashSet<ProgramGoalsParentTarget>(
			0);

	// Constructors

	/** default constructor */
	public CdCollegeType() {
	}

	/** minimal constructor */
	public CdCollegeType(String description, Double collegeGpa,
			Integer collegeSat, Integer collegeAct) {
		this.description = description;
		this.collegeGpa = collegeGpa;
		this.collegeSat = collegeSat;
		this.collegeAct = collegeAct;
	}

	/** full constructor */
	public CdCollegeType(String description, Double collegeGpa,
			Integer collegeSat, Integer collegeAct,
			Set<ProgramGoalsParentTarget> programGoalsParentTargets) {
		this.description = description;
		this.collegeGpa = collegeGpa;
		this.collegeSat = collegeSat;
		this.collegeAct = collegeAct;
		this.programGoalsParentTargets = programGoalsParentTargets;
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

	@Column(name = "DESCRIPTION", nullable = false, length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "COLLEGE_GPA", nullable = false, precision = 5)
	public Double getCollegeGpa() {
		return this.collegeGpa;
	}

	public void setCollegeGpa(Double collegeGpa) {
		this.collegeGpa = collegeGpa;
	}

	@Column(name = "COLLEGE_SAT", nullable = false)
	public Integer getCollegeSat() {
		return this.collegeSat;
	}

	public void setCollegeSat(Integer collegeSat) {
		this.collegeSat = collegeSat;
	}

	@Column(name = "COLLEGE_ACT", nullable = false)
	public Integer getCollegeAct() {
		return this.collegeAct;
	}

	public void setCollegeAct(Integer collegeAct) {
		this.collegeAct = collegeAct;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdCollegeType")
	public Set<ProgramGoalsParentTarget> getProgramGoalsParentTargets() {
		return this.programGoalsParentTargets;
	}

	public void setProgramGoalsParentTargets(
			Set<ProgramGoalsParentTarget> programGoalsParentTargets) {
		this.programGoalsParentTargets = programGoalsParentTargets;
	}

}