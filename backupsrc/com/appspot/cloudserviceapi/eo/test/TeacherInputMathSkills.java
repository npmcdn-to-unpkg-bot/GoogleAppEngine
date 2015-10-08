package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TeacherInputMathSkills entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher_input_math_skills", catalog = "eol2")
public class TeacherInputMathSkills implements java.io.Serializable {

	// Fields

	private Long id;
	private TeacherInput teacherInput;
	private Short computationAccuracy;
	private Short calculatorUse;
	private Short retention;
	private Short formulaSelection;
	private Short abstractConceptGrasp;
	private Short logicalThoughtProcess;
	private Short lowProblemProficiency;
	private Short medProblemProficiency;
	private Short hiProblemProficiency;
	private Short singleStepProblemSkill;
	private Short multiStepProblemSkill;

	// Constructors

	/** default constructor */
	public TeacherInputMathSkills() {
	}

	/** full constructor */
	public TeacherInputMathSkills(TeacherInput teacherInput,
			Short computationAccuracy, Short calculatorUse, Short retention,
			Short formulaSelection, Short abstractConceptGrasp,
			Short logicalThoughtProcess, Short lowProblemProficiency,
			Short medProblemProficiency, Short hiProblemProficiency,
			Short singleStepProblemSkill, Short multiStepProblemSkill) {
		this.teacherInput = teacherInput;
		this.computationAccuracy = computationAccuracy;
		this.calculatorUse = calculatorUse;
		this.retention = retention;
		this.formulaSelection = formulaSelection;
		this.abstractConceptGrasp = abstractConceptGrasp;
		this.logicalThoughtProcess = logicalThoughtProcess;
		this.lowProblemProficiency = lowProblemProficiency;
		this.medProblemProficiency = medProblemProficiency;
		this.hiProblemProficiency = hiProblemProficiency;
		this.singleStepProblemSkill = singleStepProblemSkill;
		this.multiStepProblemSkill = multiStepProblemSkill;
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
	@JoinColumn(name = "TEACHER_INPUT_ID", nullable = false)
	public TeacherInput getTeacherInput() {
		return this.teacherInput;
	}

	public void setTeacherInput(TeacherInput teacherInput) {
		this.teacherInput = teacherInput;
	}

	@Column(name = "COMPUTATION_ACCURACY", nullable = false)
	public Short getComputationAccuracy() {
		return this.computationAccuracy;
	}

	public void setComputationAccuracy(Short computationAccuracy) {
		this.computationAccuracy = computationAccuracy;
	}

	@Column(name = "CALCULATOR_USE", nullable = false)
	public Short getCalculatorUse() {
		return this.calculatorUse;
	}

	public void setCalculatorUse(Short calculatorUse) {
		this.calculatorUse = calculatorUse;
	}

	@Column(name = "RETENTION", nullable = false)
	public Short getRetention() {
		return this.retention;
	}

	public void setRetention(Short retention) {
		this.retention = retention;
	}

	@Column(name = "FORMULA_SELECTION", nullable = false)
	public Short getFormulaSelection() {
		return this.formulaSelection;
	}

	public void setFormulaSelection(Short formulaSelection) {
		this.formulaSelection = formulaSelection;
	}

	@Column(name = "ABSTRACT_CONCEPT_GRASP", nullable = false)
	public Short getAbstractConceptGrasp() {
		return this.abstractConceptGrasp;
	}

	public void setAbstractConceptGrasp(Short abstractConceptGrasp) {
		this.abstractConceptGrasp = abstractConceptGrasp;
	}

	@Column(name = "LOGICAL_THOUGHT_PROCESS", nullable = false)
	public Short getLogicalThoughtProcess() {
		return this.logicalThoughtProcess;
	}

	public void setLogicalThoughtProcess(Short logicalThoughtProcess) {
		this.logicalThoughtProcess = logicalThoughtProcess;
	}

	@Column(name = "LOW_PROBLEM_PROFICIENCY", nullable = false)
	public Short getLowProblemProficiency() {
		return this.lowProblemProficiency;
	}

	public void setLowProblemProficiency(Short lowProblemProficiency) {
		this.lowProblemProficiency = lowProblemProficiency;
	}

	@Column(name = "MED_PROBLEM_PROFICIENCY", nullable = false)
	public Short getMedProblemProficiency() {
		return this.medProblemProficiency;
	}

	public void setMedProblemProficiency(Short medProblemProficiency) {
		this.medProblemProficiency = medProblemProficiency;
	}

	@Column(name = "HI_PROBLEM_PROFICIENCY", nullable = false)
	public Short getHiProblemProficiency() {
		return this.hiProblemProficiency;
	}

	public void setHiProblemProficiency(Short hiProblemProficiency) {
		this.hiProblemProficiency = hiProblemProficiency;
	}

	@Column(name = "SINGLE_STEP_PROBLEM_SKILL", nullable = false)
	public Short getSingleStepProblemSkill() {
		return this.singleStepProblemSkill;
	}

	public void setSingleStepProblemSkill(Short singleStepProblemSkill) {
		this.singleStepProblemSkill = singleStepProblemSkill;
	}

	@Column(name = "MULTI_STEP_PROBLEM_SKILL", nullable = false)
	public Short getMultiStepProblemSkill() {
		return this.multiStepProblemSkill;
	}

	public void setMultiStepProblemSkill(Short multiStepProblemSkill) {
		this.multiStepProblemSkill = multiStepProblemSkill;
	}

}