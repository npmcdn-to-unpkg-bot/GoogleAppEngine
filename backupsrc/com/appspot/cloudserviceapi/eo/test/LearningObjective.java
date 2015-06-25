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

import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LearningObjective entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "learning_objective", catalog = "eol2")

@SqlResultSetMapping(name="learningObjective", entities=@EntityResult(entityClass=LearningObjective.class))

@NamedNativeQueries({
	
	@NamedNativeQuery(name="learningObjective_for_topic_goal", 
			query="select lo.* from learning_objective lo" +
					"where lo.topic_Id=:topicId and lo.goal_Id=:goalId", resultSetMapping="learningObjective")
})


public class LearningObjective implements java.io.Serializable {

	// Fields

	private Long id;
	private Goal goal;
	private String name;
	private String text;
	private Long topicId;
	private Integer sortOrder;
	private Date updatedDt;
	private Long updatedBy;
	private Set<AssessmentItem> assessmentItems = new HashSet<AssessmentItem>(0);
	private Set<Screen> screens = new HashSet<Screen>(0);
	private Set<StudentLearningCompletedStats> studentLearningCompletedStats = new HashSet<StudentLearningCompletedStats>(0);

	// Constructors

	/** default constructor */
	public LearningObjective() {
	}

	/** minimal constructor */
	public LearningObjective(Goal goal, Date updatedDt, Long updatedBy) {
		this.goal = goal;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public LearningObjective(Goal goal, String name, String text, Long topicId,
			Integer sortOrder, Date updatedDt, Long updatedBy,
			Set<AssessmentItem> assessmentItems, Set<Screen> screens) {
		this.goal = goal;
		this.name = name;
		this.text = text;
		this.topicId = topicId;
		this.sortOrder = sortOrder;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.assessmentItems = assessmentItems;
		this.screens = screens;
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
	@JoinColumn(name = "GOAL_ID", nullable = false)
	public Goal getGoal() {
		return this.goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TEXT", length = 500)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "TOPIC_ID")
	public Long getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learningObjective")
	public Set<AssessmentItem> getAssessmentItems() {
		return this.assessmentItems;
	}

	public void setAssessmentItems(Set<AssessmentItem> assessmentItems) {
		this.assessmentItems = assessmentItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learningObjective")
	public Set<Screen> getScreens() {
		return this.screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "learningObjective")
	public Set<StudentLearningCompletedStats> getStudentLearningCompletedStats() {
		return studentLearningCompletedStats;
	}

	public void setStudentLearningCompletedStats(
			Set<StudentLearningCompletedStats> studentLearningCompletedStats) {
		this.studentLearningCompletedStats = studentLearningCompletedStats;
	}
	
	

}