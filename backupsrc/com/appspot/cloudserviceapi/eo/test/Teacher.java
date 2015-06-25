package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher", catalog = "eol2")
public class Teacher implements java.io.Serializable {

	// Fields

	private Long id;
	private Long profileId;
	private String teacherAttn;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(Long profileId) {
		this.profileId = profileId;
	}

	/** full constructor */
	public Teacher(Long profileId, String teacherAttn) {
		this.profileId = profileId;
		this.teacherAttn = teacherAttn;
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

	@Column(name = "PROFILE_ID", nullable = false)
	public Long getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	@Column(name = "TEACHER_ATTN")
	public String getTeacherAttn() {
		return this.teacherAttn;
	}

	public void setTeacherAttn(String teacherAttn) {
		this.teacherAttn = teacherAttn;
	}

}