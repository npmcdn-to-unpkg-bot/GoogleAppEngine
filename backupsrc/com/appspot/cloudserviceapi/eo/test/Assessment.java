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
 * Assessment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment", catalog = "eol2")


@SqlResultSetMapping(name="assessment", entities=@EntityResult(entityClass=Assessment.class))
@NamedNativeQueries({
	/*Does this help*/
	@NamedNativeQuery(name="ass_met", query="select a.* from assessment a", resultSetMapping="assessment"
)

}
)
public class Assessment implements java.io.Serializable {

	// Fields

	private Long id;
	private Topic topic;
	private Strand strand;
	private String name;
	private String description;
	private Short gradeStart;
	private Short gradeEnd;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<AssessmentDetail> assessmentDetails = new HashSet<AssessmentDetail>(
			0);

	// Constructors

	/** default constructor */
	public Assessment() {
	}

	/** minimal constructor */
	public Assessment(String name, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy) {
		this.name = name;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public Assessment(Topic topic, Strand strand, String name,
			String description, Short gradeStart, Short gradeEnd, Date addedDt,
			Long addedBy, Date updatedDt, Long updatedBy,
			Set<AssessmentDetail> assessmentDetails) {
		this.topic = topic;
		this.strand = strand;
		this.name = name;
		this.description = description;
		this.gradeStart = gradeStart;
		this.gradeEnd = gradeEnd;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.assessmentDetails = assessmentDetails;
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
	@JoinColumn(name = "STRAND_ID")
	public Strand getStrand() {
		return this.strand;
	}

	public void setStrand(Strand strand) {
		this.strand = strand;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "GRADE_START")
	public Short getGradeStart() {
		return this.gradeStart;
	}

	public void setGradeStart(Short gradeStart) {
		this.gradeStart = gradeStart;
	}

	@Column(name = "GRADE_END")
	public Short getGradeEnd() {
		return this.gradeEnd;
	}

	public void setGradeEnd(Short gradeEnd) {
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessment")
	public Set<AssessmentDetail> getAssessmentDetails() {
		return this.assessmentDetails;
	}

	public void setAssessmentDetails(Set<AssessmentDetail> assessmentDetails) {
		this.assessmentDetails = assessmentDetails;
	}

}