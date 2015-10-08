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
 * CdAssessmentDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_assessment_details", catalog = "eol2")
public class CdAssessmentDetails implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<ProgramGoalsAssessmentSched> programGoalsAssessmentScheds = new HashSet<ProgramGoalsAssessmentSched>(
			0);

	// Constructors

	/** default constructor */
	public CdAssessmentDetails() {
	}

	/** minimal constructor */
	public CdAssessmentDetails(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdAssessmentDetails(String name,
			Set<ProgramGoalsAssessmentSched> programGoalsAssessmentScheds) {
		this.name = name;
		this.programGoalsAssessmentScheds = programGoalsAssessmentScheds;
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

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdAssessmentDetails")
	public Set<ProgramGoalsAssessmentSched> getProgramGoalsAssessmentScheds() {
		return this.programGoalsAssessmentScheds;
	}

	public void setProgramGoalsAssessmentScheds(
			Set<ProgramGoalsAssessmentSched> programGoalsAssessmentScheds) {
		this.programGoalsAssessmentScheds = programGoalsAssessmentScheds;
	}

}