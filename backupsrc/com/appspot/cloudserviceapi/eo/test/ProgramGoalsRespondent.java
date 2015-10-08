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

/**
 * ProgramGoalsRespondent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_respondent", catalog = "eol2")
public class ProgramGoalsRespondent implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profileByStudentId;
	private Strand strand;
	private Profile profileByTeacherId;
	private Date respondentDt;
	private String respondentType;
	private Byte studentRank;
	private Double gradePercentage;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<ProgramGoalsSkillSets> programGoalsSkillSetses = new HashSet<ProgramGoalsSkillSets>(
			0);
	private Set<ProgramGoalsProgramSched> programGoalsProgramScheds = new HashSet<ProgramGoalsProgramSched>(
			0);
	private Set<ProgramGoalsParentTarget> programGoalsParentTargets = new HashSet<ProgramGoalsParentTarget>(
			0);
	private Set<ProgramGoalsGradeScale> programGoalsGradeScales = new HashSet<ProgramGoalsGradeScale>(
			0);
	private Set<ProgramGoalsAssessmentSched> programGoalsAssessmentScheds = new HashSet<ProgramGoalsAssessmentSched>(
			0);
	private Set<ProgramGoalsWorkHabits> programGoalsWorkHabitses = new HashSet<ProgramGoalsWorkHabits>(
			0);
	private Set<ProgramGoalsAppliedKnowledge> programGoalsAppliedKnowledges = new HashSet<ProgramGoalsAppliedKnowledge>(
			0);
	private Set<ProgramGoalsLearningStyle> programGoalsLearningStyles = new HashSet<ProgramGoalsLearningStyle>(
			0);
	private Set<ProgramGoalsSchedule> programGoalsSchedules = new HashSet<ProgramGoalsSchedule>(
			0);

	// Constructors

	/** default constructor */
	public ProgramGoalsRespondent() {
	}

	/** minimal constructor */
	public ProgramGoalsRespondent(Profile profileByStudentId, Strand strand,
			Profile profileByTeacherId, Date respondentDt, Byte studentRank,
			Double gradePercentage, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy) {
		this.profileByStudentId = profileByStudentId;
		this.strand = strand;
		this.profileByTeacherId = profileByTeacherId;
		this.respondentDt = respondentDt;
		this.studentRank = studentRank;
		this.gradePercentage = gradePercentage;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public ProgramGoalsRespondent(Profile profileByStudentId, Strand strand,
			Profile profileByTeacherId, Date respondentDt,
			String respondentType, Byte studentRank, Double gradePercentage,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy,
			Set<ProgramGoalsSkillSets> programGoalsSkillSetses,
			Set<ProgramGoalsProgramSched> programGoalsProgramScheds,
			Set<ProgramGoalsParentTarget> programGoalsParentTargets,
			Set<ProgramGoalsGradeScale> programGoalsGradeScales,
			Set<ProgramGoalsAssessmentSched> programGoalsAssessmentScheds,
			Set<ProgramGoalsWorkHabits> programGoalsWorkHabitses,
			Set<ProgramGoalsAppliedKnowledge> programGoalsAppliedKnowledges,
			Set<ProgramGoalsLearningStyle> programGoalsLearningStyles,
			Set<ProgramGoalsSchedule> programGoalsSchedules) {
		this.profileByStudentId = profileByStudentId;
		this.strand = strand;
		this.profileByTeacherId = profileByTeacherId;
		this.respondentDt = respondentDt;
		this.respondentType = respondentType;
		this.studentRank = studentRank;
		this.gradePercentage = gradePercentage;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.programGoalsSkillSetses = programGoalsSkillSetses;
		this.programGoalsProgramScheds = programGoalsProgramScheds;
		this.programGoalsParentTargets = programGoalsParentTargets;
		this.programGoalsGradeScales = programGoalsGradeScales;
		this.programGoalsAssessmentScheds = programGoalsAssessmentScheds;
		this.programGoalsWorkHabitses = programGoalsWorkHabitses;
		this.programGoalsAppliedKnowledges = programGoalsAppliedKnowledges;
		this.programGoalsLearningStyles = programGoalsLearningStyles;
		this.programGoalsSchedules = programGoalsSchedules;
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
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	public Profile getProfileByStudentId() {
		return this.profileByStudentId;
	}

	public void setProfileByStudentId(Profile profileByStudentId) {
		this.profileByStudentId = profileByStudentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID", nullable = false)
	public Strand getStrand() {
		return this.strand;
	}

	public void setStrand(Strand strand) {
		this.strand = strand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEACHER_ID", nullable = false)
	public Profile getProfileByTeacherId() {
		return this.profileByTeacherId;
	}

	public void setProfileByTeacherId(Profile profileByTeacherId) {
		this.profileByTeacherId = profileByTeacherId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RESPONDENT_DT", nullable = false, length = 10)
	public Date getRespondentDt() {
		return this.respondentDt;
	}

	public void setRespondentDt(Date respondentDt) {
		this.respondentDt = respondentDt;
	}

	@Column(name = "RESPONDENT_TYPE", length = 7)
	public String getRespondentType() {
		return this.respondentType;
	}

	public void setRespondentType(String respondentType) {
		this.respondentType = respondentType;
	}

	@Column(name = "STUDENT_RANK", nullable = false, precision = 2, scale = 0)
	public Byte getStudentRank() {
		return this.studentRank;
	}

	public void setStudentRank(Byte studentRank) {
		this.studentRank = studentRank;
	}

	@Column(name = "GRADE_PERCENTAGE", nullable = false, precision = 5)
	public Double getGradePercentage() {
		return this.gradePercentage;
	}

	public void setGradePercentage(Double gradePercentage) {
		this.gradePercentage = gradePercentage;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsSkillSets> getProgramGoalsSkillSetses() {
		return this.programGoalsSkillSetses;
	}

	public void setProgramGoalsSkillSetses(
			Set<ProgramGoalsSkillSets> programGoalsSkillSetses) {
		this.programGoalsSkillSetses = programGoalsSkillSetses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsProgramSched> getProgramGoalsProgramScheds() {
		return this.programGoalsProgramScheds;
	}

	public void setProgramGoalsProgramScheds(
			Set<ProgramGoalsProgramSched> programGoalsProgramScheds) {
		this.programGoalsProgramScheds = programGoalsProgramScheds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsParentTarget> getProgramGoalsParentTargets() {
		return this.programGoalsParentTargets;
	}

	public void setProgramGoalsParentTargets(
			Set<ProgramGoalsParentTarget> programGoalsParentTargets) {
		this.programGoalsParentTargets = programGoalsParentTargets;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsGradeScale> getProgramGoalsGradeScales() {
		return this.programGoalsGradeScales;
	}

	public void setProgramGoalsGradeScales(
			Set<ProgramGoalsGradeScale> programGoalsGradeScales) {
		this.programGoalsGradeScales = programGoalsGradeScales;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsAssessmentSched> getProgramGoalsAssessmentScheds() {
		return this.programGoalsAssessmentScheds;
	}

	public void setProgramGoalsAssessmentScheds(
			Set<ProgramGoalsAssessmentSched> programGoalsAssessmentScheds) {
		this.programGoalsAssessmentScheds = programGoalsAssessmentScheds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsWorkHabits> getProgramGoalsWorkHabitses() {
		return this.programGoalsWorkHabitses;
	}

	public void setProgramGoalsWorkHabitses(
			Set<ProgramGoalsWorkHabits> programGoalsWorkHabitses) {
		this.programGoalsWorkHabitses = programGoalsWorkHabitses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsAppliedKnowledge> getProgramGoalsAppliedKnowledges() {
		return this.programGoalsAppliedKnowledges;
	}

	public void setProgramGoalsAppliedKnowledges(
			Set<ProgramGoalsAppliedKnowledge> programGoalsAppliedKnowledges) {
		this.programGoalsAppliedKnowledges = programGoalsAppliedKnowledges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsLearningStyle> getProgramGoalsLearningStyles() {
		return this.programGoalsLearningStyles;
	}

	public void setProgramGoalsLearningStyles(
			Set<ProgramGoalsLearningStyle> programGoalsLearningStyles) {
		this.programGoalsLearningStyles = programGoalsLearningStyles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "programGoalsRespondent")
	public Set<ProgramGoalsSchedule> getProgramGoalsSchedules() {
		return this.programGoalsSchedules;
	}

	public void setProgramGoalsSchedules(
			Set<ProgramGoalsSchedule> programGoalsSchedules) {
		this.programGoalsSchedules = programGoalsSchedules;
	}

}