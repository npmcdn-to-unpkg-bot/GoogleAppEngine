package com.appspot.cloudserviceapi.eo.test;

import java.sql.Time;
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

/**
 * ProgramGoalsSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "program_goals_schedule", catalog = "eol2")
public class ProgramGoalsSchedule implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profile;
	private Subject subject;
	private ProgramGoalsRespondent programGoalsRespondent;
	private Short schedDay;
	private Time schedBgnTim;
	private Time schedEndTim;
	private String activityType;
	private String level;
	private Date activityDt;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public ProgramGoalsSchedule() {
	}

	/** minimal constructor */
	public ProgramGoalsSchedule(Profile profile, Subject subject,
			ProgramGoalsRespondent programGoalsRespondent, Short schedDay,
			Time schedBgnTim, Time schedEndTim, String activityType,
			String level, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy) {
		this.profile = profile;
		this.subject = subject;
		this.programGoalsRespondent = programGoalsRespondent;
		this.schedDay = schedDay;
		this.schedBgnTim = schedBgnTim;
		this.schedEndTim = schedEndTim;
		this.activityType = activityType;
		this.level = level;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public ProgramGoalsSchedule(Profile profile, Subject subject,
			ProgramGoalsRespondent programGoalsRespondent, Short schedDay,
			Time schedBgnTim, Time schedEndTim, String activityType,
			String level, Date activityDt, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.profile = profile;
		this.subject = subject;
		this.programGoalsRespondent = programGoalsRespondent;
		this.schedDay = schedDay;
		this.schedBgnTim = schedBgnTim;
		this.schedEndTim = schedEndTim;
		this.activityType = activityType;
		this.level = level;
		this.activityDt = activityDt;
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
	@JoinColumn(name = "PROFILE_ID", nullable = false)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID", nullable = false)
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESPONDENT_ID", nullable = false)
	public ProgramGoalsRespondent getProgramGoalsRespondent() {
		return this.programGoalsRespondent;
	}

	public void setProgramGoalsRespondent(
			ProgramGoalsRespondent programGoalsRespondent) {
		this.programGoalsRespondent = programGoalsRespondent;
	}

	@Column(name = "SCHED_DAY", nullable = false)
	public Short getSchedDay() {
		return this.schedDay;
	}

	public void setSchedDay(Short schedDay) {
		this.schedDay = schedDay;
	}

	@Column(name = "SCHED_BGN_TIM", nullable = false, length = 8)
	public Time getSchedBgnTim() {
		return this.schedBgnTim;
	}

	public void setSchedBgnTim(Time schedBgnTim) {
		this.schedBgnTim = schedBgnTim;
	}

	@Column(name = "SCHED_END_TIM", nullable = false, length = 8)
	public Time getSchedEndTim() {
		return this.schedEndTim;
	}

	public void setSchedEndTim(Time schedEndTim) {
		this.schedEndTim = schedEndTim;
	}

	@Column(name = "ACTIVITY_TYPE", nullable = false, length = 1)
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Column(name = "LEVEL", nullable = false, length = 1)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIVITY_DT", length = 10)
	public Date getActivityDt() {
		return this.activityDt;
	}

	public void setActivityDt(Date activityDt) {
		this.activityDt = activityDt;
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