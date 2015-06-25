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
 * CdSubject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_subject", catalog = "eol2")
public class CdSubject implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<StudentResults> studentResultses = new HashSet<StudentResults>(
			0);
	private Set<AlertLog> alertLogs = new HashSet<AlertLog>(0);
	private Set<StudentTeacherPair> studentTeacherPairs = new HashSet<StudentTeacherPair>(
			0);
	private Set<StudentActivityLog> studentActivityLogs = new HashSet<StudentActivityLog>(
			0);
	private Set<TeacherInput> teacherInputs = new HashSet<TeacherInput>(0);

	// Constructors

	/** default constructor */
	public CdSubject() {
	}

	/** minimal constructor */
	public CdSubject(String name) {
		this.name = name;
	}

	/** full constructor */
	public CdSubject(String name, Set<StudentResults> studentResultses,
			Set<AlertLog> alertLogs,
			Set<StudentTeacherPair> studentTeacherPairs,
			Set<StudentActivityLog> studentActivityLogs,
			Set<TeacherInput> teacherInputs) {
		this.name = name;
		this.studentResultses = studentResultses;
		this.alertLogs = alertLogs;
		this.studentTeacherPairs = studentTeacherPairs;
		this.studentActivityLogs = studentActivityLogs;
		this.teacherInputs = teacherInputs;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdSubject")
	public Set<StudentResults> getStudentResultses() {
		return this.studentResultses;
	}

	public void setStudentResultses(Set<StudentResults> studentResultses) {
		this.studentResultses = studentResultses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdSubject")
	public Set<AlertLog> getAlertLogs() {
		return this.alertLogs;
	}

	public void setAlertLogs(Set<AlertLog> alertLogs) {
		this.alertLogs = alertLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdSubject")
	public Set<StudentTeacherPair> getStudentTeacherPairs() {
		return this.studentTeacherPairs;
	}

	public void setStudentTeacherPairs(
			Set<StudentTeacherPair> studentTeacherPairs) {
		this.studentTeacherPairs = studentTeacherPairs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdSubject")
	public Set<StudentActivityLog> getStudentActivityLogs() {
		return this.studentActivityLogs;
	}

	public void setStudentActivityLogs(
			Set<StudentActivityLog> studentActivityLogs) {
		this.studentActivityLogs = studentActivityLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdSubject")
	public Set<TeacherInput> getTeacherInputs() {
		return this.teacherInputs;
	}

	public void setTeacherInputs(Set<TeacherInput> teacherInputs) {
		this.teacherInputs = teacherInputs;
	}

}