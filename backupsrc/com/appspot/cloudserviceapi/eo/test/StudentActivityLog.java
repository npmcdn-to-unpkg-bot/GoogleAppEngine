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
import javax.persistence.UniqueConstraint;

/**
 * StudentActivityLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_activity_log", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"STUDENT_ID", "SUBJECT_ID", "ADDED_DT" }))
public class StudentActivityLog implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profile;
	private CdSubject cdSubject;
	private Time startTm;
	private Time endTm;
	private Long activityId;
	private String activityType;
	private Long topicId;
	private Long learningObjectiveId;
	private Long sessionId;
	private String problemDesc;
	private String answerDesc;
	private String indicator;
	private Long otherId;
	private String problemResult;
	private Date addedDt;
	private Long addedBy;

	// Constructors

	/** default constructor */
	public StudentActivityLog() {
	}

	/** minimal constructor */
	public StudentActivityLog(Profile profile, CdSubject cdSubject,
			Time startTm, Time endTm, Long activityId, String activityType,
			Long topicId, Long learningObjectiveId, Long sessionId,
			String indicator, Long otherId, Date addedDt, Long addedBy) {
		this.profile = profile;
		this.cdSubject = cdSubject;
		this.startTm = startTm;
		this.endTm = endTm;
		this.activityId = activityId;
		this.activityType = activityType;
		this.topicId = topicId;
		this.learningObjectiveId = learningObjectiveId;
		this.sessionId = sessionId;
		this.indicator = indicator;
		this.otherId = otherId;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
	}

	/** full constructor */
	public StudentActivityLog(Profile profile, CdSubject cdSubject,
			Time startTm, Time endTm, Long activityId, String activityType,
			Long topicId, Long learningObjectiveId, Long sessionId,
			String problemDesc, String answerDesc, String indicator,
			Long otherId, String problemResult, Date addedDt, Long addedBy) {
		this.profile = profile;
		this.cdSubject = cdSubject;
		this.startTm = startTm;
		this.endTm = endTm;
		this.activityId = activityId;
		this.activityType = activityType;
		this.topicId = topicId;
		this.learningObjectiveId = learningObjectiveId;
		this.sessionId = sessionId;
		this.problemDesc = problemDesc;
		this.answerDesc = answerDesc;
		this.indicator = indicator;
		this.otherId = otherId;
		this.problemResult = problemResult;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
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
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID", nullable = false)
	public CdSubject getCdSubject() {
		return this.cdSubject;
	}

	public void setCdSubject(CdSubject cdSubject) {
		this.cdSubject = cdSubject;
	}

	@Column(name = "START_TM", nullable = false, length = 8)
	public Time getStartTm() {
		return this.startTm;
	}

	public void setStartTm(Time startTm) {
		this.startTm = startTm;
	}

	@Column(name = "END_TM", nullable = false, length = 8)
	public Time getEndTm() {
		return this.endTm;
	}

	public void setEndTm(Time endTm) {
		this.endTm = endTm;
	}

	@Column(name = "ACTIVITY_ID", nullable = false)
	public Long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column(name = "ACTIVITY_TYPE", nullable = false, length = 1)
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Column(name = "TOPIC_ID", nullable = false)
	public Long getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@Column(name = "LEARNING_OBJECTIVE_ID", nullable = false)
	public Long getLearningObjectiveId() {
		return this.learningObjectiveId;
	}

	public void setLearningObjectiveId(Long learningObjectiveId) {
		this.learningObjectiveId = learningObjectiveId;
	}

	@Column(name = "SESSION_ID", nullable = false)
	public Long getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "PROBLEM_DESC", length = 65535)
	public String getProblemDesc() {
		return this.problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	@Column(name = "ANSWER_DESC", length = 65535)
	public String getAnswerDesc() {
		return this.answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	@Column(name = "INDICATOR", nullable = false, length = 4)
	public String getIndicator() {
		return this.indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	@Column(name = "OTHER_ID", nullable = false)
	public Long getOtherId() {
		return this.otherId;
	}

	public void setOtherId(Long otherId) {
		this.otherId = otherId;
	}

	@Column(name = "PROBLEM_RESULT", length = 21)
	public String getProblemResult() {
		return this.problemResult;
	}

	public void setProblemResult(String problemResult) {
		this.problemResult = problemResult;
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

}