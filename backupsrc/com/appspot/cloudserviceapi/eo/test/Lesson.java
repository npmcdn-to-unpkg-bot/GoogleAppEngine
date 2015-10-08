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
 * Lesson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lesson", catalog = "eol2")
public class Lesson implements java.io.Serializable {

	// Fields

	private Long id;
	private Topic topic;
	private CdLessonType cdLessonType;
	private String name;
	private String description;
	private String status;
	private Integer gradeStart;
	private Integer gradeEnd;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<LessonScreen> lessonScreens = new HashSet<LessonScreen>(0);

	// Constructors

	/** default constructor */
	public Lesson() {
	}

	/** minimal constructor */
	public Lesson(CdLessonType cdLessonType, String name, Date addedDt,
			Long addedBy, Date updatedDt, Long updatedBy) {
		this.cdLessonType = cdLessonType;
		this.name = name;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public Lesson(Topic topic, CdLessonType cdLessonType, String name,
			String description, String status, Integer gradeStart,
			Integer gradeEnd, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy, Set<LessonScreen> lessonScreens) {
		this.topic = topic;
		this.cdLessonType = cdLessonType;
		this.name = name;
		this.description = description;
		this.status = status;
		this.gradeStart = gradeStart;
		this.gradeEnd = gradeEnd;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.lessonScreens = lessonScreens;
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
	@JoinColumn(name = "TOPIC_ID")
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_LESSON_TYPE_ID", nullable = false)
	public CdLessonType getCdLessonType() {
		return this.cdLessonType;
	}

	public void setCdLessonType(CdLessonType cdLessonType) {
		this.cdLessonType = cdLessonType;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "GRADE_START")
	public Integer getGradeStart() {
		return this.gradeStart;
	}

	public void setGradeStart(Integer gradeStart) {
		this.gradeStart = gradeStart;
	}

	@Column(name = "GRADE_END")
	public Integer getGradeEnd() {
		return this.gradeEnd;
	}

	public void setGradeEnd(Integer gradeEnd) {
		this.gradeEnd = gradeEnd;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lesson")
	public Set<LessonScreen> getLessonScreens() {
		return this.lessonScreens;
	}

	public void setLessonScreens(Set<LessonScreen> lessonScreens) {
		this.lessonScreens = lessonScreens;
	}

}