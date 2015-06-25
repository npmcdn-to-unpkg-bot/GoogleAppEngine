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
 * GraphicTypeLearningStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "graphic_type_learning_style", catalog = "eol2")
public class GraphicTypeLearningStyle implements java.io.Serializable {

	// Fields

	private Long id;
	private CdGraphicType cdGraphicType;
	private CdLearningStyle cdLearningStyle;
	private Integer priority;

	// Constructors

	/** default constructor */
	public GraphicTypeLearningStyle() {
	}

	/** minimal constructor */
	public GraphicTypeLearningStyle(CdGraphicType cdGraphicType,
			CdLearningStyle cdLearningStyle) {
		this.cdGraphicType = cdGraphicType;
		this.cdLearningStyle = cdLearningStyle;
	}

	/** full constructor */
	public GraphicTypeLearningStyle(CdGraphicType cdGraphicType,
			CdLearningStyle cdLearningStyle, Integer priority) {
		this.cdGraphicType = cdGraphicType;
		this.cdLearningStyle = cdLearningStyle;
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
	@JoinColumn(name = "CD_GRAPHIC_TYPE_ID", nullable = false)
	public CdGraphicType getCdGraphicType() {
		return this.cdGraphicType;
	}

	public void setCdGraphicType(CdGraphicType cdGraphicType) {
		this.cdGraphicType = cdGraphicType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_LEARNING_STYLE_ID", nullable = false)
	public CdLearningStyle getCdLearningStyle() {
		return this.cdLearningStyle;
	}

	public void setCdLearningStyle(CdLearningStyle cdLearningStyle) {
		this.cdLearningStyle = cdLearningStyle;
	}

	@Column(name = "PRIORITY")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}