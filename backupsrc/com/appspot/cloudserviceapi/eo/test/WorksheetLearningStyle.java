package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * WorksheetLearningStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "worksheet_learning_style", catalog = "eol2")
public class WorksheetLearningStyle implements java.io.Serializable {

	// Fields

	private Long id;
	private CdLearningStyle cdLearningStyle;
	private Worksheet worksheet;
	private Integer priority;

	// Constructors

	/** default constructor */
	public WorksheetLearningStyle() {
	}

	/** minimal constructor */
	public WorksheetLearningStyle(CdLearningStyle cdLearningStyle,
			Worksheet worksheet) {
		this.cdLearningStyle = cdLearningStyle;
		this.worksheet = worksheet;
	}

	/** full constructor */
	public WorksheetLearningStyle(CdLearningStyle cdLearningStyle,
			Worksheet worksheet, Integer priority) {
		this.cdLearningStyle = cdLearningStyle;
		this.worksheet = worksheet;
		this.priority = priority;
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
	@JoinColumn(name = "LEARNING_STYLE_ID", nullable = false)
	public CdLearningStyle getCdLearningStyle() {
		return this.cdLearningStyle;
	}

	public void setCdLearningStyle(CdLearningStyle cdLearningStyle) {
		this.cdLearningStyle = cdLearningStyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKSHEET_ID", nullable = false)
	public Worksheet getWorksheet() {
		return this.worksheet;
	}

	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}

	@Column(name = "PRIORITY")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}