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
 * TeacherInput entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher_input", catalog = "eol2")
public class TeacherInput implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profileByStudentId;
	private CdSubject cdSubject;
	private Profile profileByTeacherId;
	private Long teacherInputId;
	private Double gradePercentage;
	private Short learnsWellAnyStyle;
	private Short learnsBestRealWorldExamples;
	private Short learnsGivenSimplerExplanations;
	private Short visual;
	private Short auditory;
	private Short handsOn;
	private Short deductive;
	private Short inductive;
	private Short completesAssignments;
	private Short showsSolution;
	private Short focus;
	private Short accuracy;
	private Short organization;
	private Short preparation;
	private Short appliesKnowledgeRealWorld;
	private Short solvesIntegratedProblems;
	private Set<TeacherInputMathSkills> teacherInputMathSkillses = new HashSet<TeacherInputMathSkills>(
			0);

	// Constructors

	/** default constructor */
	public TeacherInput() {
	}

	/** minimal constructor */
	public TeacherInput(Profile profileByStudentId, CdSubject cdSubject,
			Profile profileByTeacherId, Double gradePercentage,
			Short learnsWellAnyStyle, Short learnsBestRealWorldExamples,
			Short learnsGivenSimplerExplanations, Short visual, Short auditory,
			Short handsOn, Short deductive, Short inductive,
			Short completesAssignments, Short showsSolution, Short focus,
			Short accuracy, Short organization, Short preparation,
			Short appliesKnowledgeRealWorld, Short solvesIntegratedProblems) {
		this.profileByStudentId = profileByStudentId;
		this.cdSubject = cdSubject;
		this.profileByTeacherId = profileByTeacherId;
		this.gradePercentage = gradePercentage;
		this.learnsWellAnyStyle = learnsWellAnyStyle;
		this.learnsBestRealWorldExamples = learnsBestRealWorldExamples;
		this.learnsGivenSimplerExplanations = learnsGivenSimplerExplanations;
		this.visual = visual;
		this.auditory = auditory;
		this.handsOn = handsOn;
		this.deductive = deductive;
		this.inductive = inductive;
		this.completesAssignments = completesAssignments;
		this.showsSolution = showsSolution;
		this.focus = focus;
		this.accuracy = accuracy;
		this.organization = organization;
		this.preparation = preparation;
		this.appliesKnowledgeRealWorld = appliesKnowledgeRealWorld;
		this.solvesIntegratedProblems = solvesIntegratedProblems;
	}

	/** full constructor */
	public TeacherInput(Profile profileByStudentId, CdSubject cdSubject,
			Profile profileByTeacherId, Long teacherInputId,
			Double gradePercentage, Short learnsWellAnyStyle,
			Short learnsBestRealWorldExamples,
			Short learnsGivenSimplerExplanations, Short visual, Short auditory,
			Short handsOn, Short deductive, Short inductive,
			Short completesAssignments, Short showsSolution, Short focus,
			Short accuracy, Short organization, Short preparation,
			Short appliesKnowledgeRealWorld, Short solvesIntegratedProblems,
			Set<TeacherInputMathSkills> teacherInputMathSkillses) {
		this.profileByStudentId = profileByStudentId;
		this.cdSubject = cdSubject;
		this.profileByTeacherId = profileByTeacherId;
		this.teacherInputId = teacherInputId;
		this.gradePercentage = gradePercentage;
		this.learnsWellAnyStyle = learnsWellAnyStyle;
		this.learnsBestRealWorldExamples = learnsBestRealWorldExamples;
		this.learnsGivenSimplerExplanations = learnsGivenSimplerExplanations;
		this.visual = visual;
		this.auditory = auditory;
		this.handsOn = handsOn;
		this.deductive = deductive;
		this.inductive = inductive;
		this.completesAssignments = completesAssignments;
		this.showsSolution = showsSolution;
		this.focus = focus;
		this.accuracy = accuracy;
		this.organization = organization;
		this.preparation = preparation;
		this.appliesKnowledgeRealWorld = appliesKnowledgeRealWorld;
		this.solvesIntegratedProblems = solvesIntegratedProblems;
		this.teacherInputMathSkillses = teacherInputMathSkillses;
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
	public CdSubject getCdSubject() {
		return this.cdSubject;
	}

	public void setCdSubject(CdSubject cdSubject) {
		this.cdSubject = cdSubject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEACHER_ID", nullable = false)
	public Profile getProfileByTeacherId() {
		return this.profileByTeacherId;
	}

	public void setProfileByTeacherId(Profile profileByTeacherId) {
		this.profileByTeacherId = profileByTeacherId;
	}

	@Column(name = "TEACHER_INPUT_ID")
	public Long getTeacherInputId() {
		return this.teacherInputId;
	}

	public void setTeacherInputId(Long teacherInputId) {
		this.teacherInputId = teacherInputId;
	}

	@Column(name = "GRADE_PERCENTAGE", nullable = false, precision = 5)
	public Double getGradePercentage() {
		return this.gradePercentage;
	}

	public void setGradePercentage(Double gradePercentage) {
		this.gradePercentage = gradePercentage;
	}

	@Column(name = "LEARNS_WELL_ANY_STYLE", nullable = false)
	public Short getLearnsWellAnyStyle() {
		return this.learnsWellAnyStyle;
	}

	public void setLearnsWellAnyStyle(Short learnsWellAnyStyle) {
		this.learnsWellAnyStyle = learnsWellAnyStyle;
	}

	@Column(name = "LEARNS_BEST_REAL_WORLD_EXAMPLES", nullable = false)
	public Short getLearnsBestRealWorldExamples() {
		return this.learnsBestRealWorldExamples;
	}

	public void setLearnsBestRealWorldExamples(Short learnsBestRealWorldExamples) {
		this.learnsBestRealWorldExamples = learnsBestRealWorldExamples;
	}

	@Column(name = "LEARNS_GIVEN_SIMPLER_EXPLANATIONS", nullable = false)
	public Short getLearnsGivenSimplerExplanations() {
		return this.learnsGivenSimplerExplanations;
	}

	public void setLearnsGivenSimplerExplanations(
			Short learnsGivenSimplerExplanations) {
		this.learnsGivenSimplerExplanations = learnsGivenSimplerExplanations;
	}

	@Column(name = "VISUAL", nullable = false)
	public Short getVisual() {
		return this.visual;
	}

	public void setVisual(Short visual) {
		this.visual = visual;
	}

	@Column(name = "AUDITORY", nullable = false)
	public Short getAuditory() {
		return this.auditory;
	}

	public void setAuditory(Short auditory) {
		this.auditory = auditory;
	}

	@Column(name = "HANDS-ON", nullable = false)
	public Short getHandsOn() {
		return this.handsOn;
	}

	public void setHandsOn(Short handsOn) {
		this.handsOn = handsOn;
	}

	@Column(name = "DEDUCTIVE", nullable = false)
	public Short getDeductive() {
		return this.deductive;
	}

	public void setDeductive(Short deductive) {
		this.deductive = deductive;
	}

	@Column(name = "INDUCTIVE", nullable = false)
	public Short getInductive() {
		return this.inductive;
	}

	public void setInductive(Short inductive) {
		this.inductive = inductive;
	}

	@Column(name = "COMPLETES_ASSIGNMENTS", nullable = false)
	public Short getCompletesAssignments() {
		return this.completesAssignments;
	}

	public void setCompletesAssignments(Short completesAssignments) {
		this.completesAssignments = completesAssignments;
	}

	@Column(name = "SHOWS_SOLUTION", nullable = false)
	public Short getShowsSolution() {
		return this.showsSolution;
	}

	public void setShowsSolution(Short showsSolution) {
		this.showsSolution = showsSolution;
	}

	@Column(name = "FOCUS", nullable = false)
	public Short getFocus() {
		return this.focus;
	}

	public void setFocus(Short focus) {
		this.focus = focus;
	}

	@Column(name = "ACCURACY", nullable = false)
	public Short getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(Short accuracy) {
		this.accuracy = accuracy;
	}

	@Column(name = "ORGANIZATION", nullable = false)
	public Short getOrganization() {
		return this.organization;
	}

	public void setOrganization(Short organization) {
		this.organization = organization;
	}

	@Column(name = "PREPARATION", nullable = false)
	public Short getPreparation() {
		return this.preparation;
	}

	public void setPreparation(Short preparation) {
		this.preparation = preparation;
	}

	@Column(name = "APPLIES_KNOWLEDGE_REAL_WORLD", nullable = false)
	public Short getAppliesKnowledgeRealWorld() {
		return this.appliesKnowledgeRealWorld;
	}

	public void setAppliesKnowledgeRealWorld(Short appliesKnowledgeRealWorld) {
		this.appliesKnowledgeRealWorld = appliesKnowledgeRealWorld;
	}

	@Column(name = "SOLVES INTEGRATED_PROBLEMS", nullable = false)
	public Short getSolvesIntegratedProblems() {
		return this.solvesIntegratedProblems;
	}

	public void setSolvesIntegratedProblems(Short solvesIntegratedProblems) {
		this.solvesIntegratedProblems = solvesIntegratedProblems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacherInput")
	public Set<TeacherInputMathSkills> getTeacherInputMathSkillses() {
		return this.teacherInputMathSkillses;
	}

	public void setTeacherInputMathSkillses(
			Set<TeacherInputMathSkills> teacherInputMathSkillses) {
		this.teacherInputMathSkillses = teacherInputMathSkillses;
	}

}