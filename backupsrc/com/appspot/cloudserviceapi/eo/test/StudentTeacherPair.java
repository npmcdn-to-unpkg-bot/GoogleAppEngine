package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * StudentTeacherPair entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_teacher_pair", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"STUDENT_ID", "TEACHER_ID", "SUBJECT_ID" }))
public class StudentTeacherPair implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profileByStudentId;
	private CdSubject cdSubject;
	private Profile profileByTeacherId;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public StudentTeacherPair() {
	}

	/** full constructor */
	public StudentTeacherPair(Profile profileByStudentId, CdSubject cdSubject,
			Profile profileByTeacherId, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.profileByStudentId = profileByStudentId;
		this.cdSubject = cdSubject;
		this.profileByTeacherId = profileByTeacherId;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
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

}