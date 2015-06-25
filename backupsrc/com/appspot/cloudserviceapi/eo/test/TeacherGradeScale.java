package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TeacherGradeScale entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher_grade_scale", catalog = "eol2")
public class TeacherGradeScale implements java.io.Serializable {

	// Fields

	private Long id;
	private Long teacherId;
	private String gradeScale;
	private Double gradeUpperBnd;
	private Double gradeLowerBnd;

	// Constructors

	/** default constructor */
	public TeacherGradeScale() {
	}

	/** minimal constructor */
	public TeacherGradeScale(Long teacherId, Double gradeUpperBnd,
			Double gradeLowerBnd) {
		this.teacherId = teacherId;
		this.gradeUpperBnd = gradeUpperBnd;
		this.gradeLowerBnd = gradeLowerBnd;
	}

	/** full constructor */
	public TeacherGradeScale(Long teacherId, String gradeScale,
			Double gradeUpperBnd, Double gradeLowerBnd) {
		this.teacherId = teacherId;
		this.gradeScale = gradeScale;
		this.gradeUpperBnd = gradeUpperBnd;
		this.gradeLowerBnd = gradeLowerBnd;
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

	@Column(name = "TEACHER_ID", nullable = false)
	public Long getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name = "GRADE_SCALE", length = 3)
	public String getGradeScale() {
		return this.gradeScale;
	}

	public void setGradeScale(String gradeScale) {
		this.gradeScale = gradeScale;
	}

	@Column(name = "GRADE_UPPER_BND", nullable = false, precision = 5)
	public Double getGradeUpperBnd() {
		return this.gradeUpperBnd;
	}

	public void setGradeUpperBnd(Double gradeUpperBnd) {
		this.gradeUpperBnd = gradeUpperBnd;
	}

	@Column(name = "GRADE_LOWER_BND", nullable = false, precision = 5)
	public Double getGradeLowerBnd() {
		return this.gradeLowerBnd;
	}

	public void setGradeLowerBnd(Double gradeLowerBnd) {
		this.gradeLowerBnd = gradeLowerBnd;
	}

}