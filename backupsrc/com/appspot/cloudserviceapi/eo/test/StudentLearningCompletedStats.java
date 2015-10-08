package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;






/**
 * StudentLearningCompletedStats entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_learning_completed_stats", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LO_ID", "PROFILE_ID"}))
		
@SqlResultSetMapping(name="student_learning_completed_stats", entities=@EntityResult(entityClass=StudentLearningCompletedStats.class))

@NamedNativeQueries({
	@NamedNativeQuery(name="student_lo_completed_stats",
			query="select s.* from student_learning_completed_stats s " +
					"where s.lo_id=:loId " +
					"and s.profile_id = :profileId " , resultSetMapping="student_learning_completed_stats")		
})
					
		
public class StudentLearningCompletedStats implements java.io.Serializable {

	// Fields

	private Long id;
	private Profile profile;
	private LearningObjective learningObjective;
	private String tdViewedInd;
	private String diViewedInd;
	private Integer wtpCompleted;
	private Integer ppCompletedTotal;
	private Integer ppCompletedCorrect;

	// Constructors

	/** default constructor */
	public StudentLearningCompletedStats() {
	}

	/** minimal constructor */
	public StudentLearningCompletedStats(Profile profile,
			LearningObjective learningObjective) {
		this.profile = profile;
		this.learningObjective = learningObjective;
	}

	/** full constructor */
	public StudentLearningCompletedStats(Profile profile,
			LearningObjective learningObjective, String tdViewedInd,
			String diViewedInd, Integer wtpCompleted, Integer ppCompletedTotal,
			Integer ppCompletedCorrect) {
		this.profile = profile;
		this.learningObjective = learningObjective;
		this.tdViewedInd = tdViewedInd;
		this.diViewedInd = diViewedInd;
		this.wtpCompleted = wtpCompleted;
		this.ppCompletedTotal = ppCompletedTotal;
		this.ppCompletedCorrect = ppCompletedCorrect;
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

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_ID", nullable = false)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "LO_ID", nullable = false)
	public LearningObjective getLearningObjective() {
		return this.learningObjective;
	}

	public void setLearningObjective(LearningObjective learningObjective) {
		this.learningObjective = learningObjective;
	}

	@Column(name = "TD_VIEWED_IND", length = 1)
	public String getTdViewedInd() {
		return this.tdViewedInd;
	}

	public void setTdViewedInd(String tdViewedInd) {
		this.tdViewedInd = tdViewedInd;
	}

	@Column(name = "DI_VIEWED_IND", length = 1)
	public String getDiViewedInd() {
		return this.diViewedInd;
	}

	public void setDiViewedInd(String diViewedInd) {
		this.diViewedInd = diViewedInd;
	}

	@Column(name = "WTP_COMPLETED")
	public Integer getWtpCompleted() {
		return this.wtpCompleted;
	}

	public void setWtpCompleted(Integer wtpCompleted) {
		this.wtpCompleted = wtpCompleted;
	}

	@Column(name = "PP_COMPLETED_TOTAL")
	public Integer getPpCompletedTotal() {
		return this.ppCompletedTotal;
	}

	public void setPpCompletedTotal(Integer ppCompletedTotal) {
		this.ppCompletedTotal = ppCompletedTotal;
	}

	@Column(name = "PP_COMPLETED_CORRECT")
	public Integer getPpCompletedCorrect() {
		return this.ppCompletedCorrect;
	}

	public void setPpCompletedCorrect(Integer ppCompletedCorrect) {
		this.ppCompletedCorrect = ppCompletedCorrect;
	}

}