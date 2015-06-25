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
 * Topic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "topic", catalog = "eol2")

@SqlResultSetMapping(name="topic", entities=@EntityResult(entityClass=Topic.class))

@NamedNativeQuery(name="topic_for_subject", 
			query="select t.* from topic t" +
					"where t.subject_Id=:subjectId", resultSetMapping="topic")


public class Topic implements java.io.Serializable {

	// Fields

	private Long id;
	private Strand strand;
	private Topic topic;
	private String name;
	private String description;
	private Integer sortOrder;
	private Date updatedDt;
	private Long updatedBy;
	private Long subjectId;
	private Set<Lesson> lessons = new HashSet<Lesson>(0);
	private Set<Goal> goals = new HashSet<Goal>(0);
	private Set<CdGraphicType> cdGraphicTypes = new HashSet<CdGraphicType>(0);
	private Set<Assessment> assessments = new HashSet<Assessment>(0);
	private Set<Topic> topics = new HashSet<Topic>(0);

	// Constructors

	/** default constructor */
	public Topic() {
	}

	/** minimal constructor */
	public Topic(String name, Date updatedDt, Long updatedBy) {
		this.name = name;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public Topic(Strand strand, Topic topic, String name, String description,
			Integer sortOrder, Date updatedDt, Long updatedBy, Long subjectId,
			Set<Lesson> lessons, Set<Goal> goals,
			Set<CdGraphicType> cdGraphicTypes, Set<Assessment> assessments,
			Set<Topic> topics) {
		this.strand = strand;
		this.topic = topic;
		this.name = name;
		this.description = description;
		this.sortOrder = sortOrder;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.subjectId = subjectId;
		this.lessons = lessons;
		this.goals = goals;
		this.cdGraphicTypes = cdGraphicTypes;
		this.assessments = assessments;
		this.topics = topics;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STRAND_ID")
	public Strand getStrand() {
		return this.strand;
	}

	public void setStrand(Strand strand) {
		this.strand = strand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_TOPIC_ID")
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
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

	@Column(name = "SUBJECT_ID")
	public Long getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topic")
	public Set<Lesson> getLessons() {
		return this.lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topic")
	public Set<Goal> getGoals() {
		return this.goals;
	}

	public void setGoals(Set<Goal> goals) {
		this.goals = goals;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topic")
	public Set<CdGraphicType> getCdGraphicTypes() {
		return this.cdGraphicTypes;
	}

	public void setCdGraphicTypes(Set<CdGraphicType> cdGraphicTypes) {
		this.cdGraphicTypes = cdGraphicTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topic")
	public Set<Assessment> getAssessments() {
		return this.assessments;
	}

	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topic")
	public Set<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

}