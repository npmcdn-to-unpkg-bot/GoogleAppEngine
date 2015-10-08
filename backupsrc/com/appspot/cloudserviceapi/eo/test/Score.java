package com.appspot.cloudserviceapi.eo.test;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Score entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "score", catalog = "eol2")
public class Score implements java.io.Serializable {

	// Fields

	private Long id;
	private Long studentId;
	private Long topicId;
	private Integer score;
	private Timestamp timestamp;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** minimal constructor */
	public Score(Long studentId, Long topicId, Timestamp timestamp) {
		this.studentId = studentId;
		this.topicId = topicId;
		this.timestamp = timestamp;
	}

	/** full constructor */
	public Score(Long studentId, Long topicId, Integer score,
			Timestamp timestamp) {
		this.studentId = studentId;
		this.topicId = topicId;
		this.score = score;
		this.timestamp = timestamp;
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

	@Column(name = "STUDENT_ID", nullable = false)
	public Long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(name = "TOPIC_ID", nullable = false)
	public Long getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@Column(name = "SCORE")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "TIMESTAMP", nullable = false, length = 19)
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}