package com.appspot.cloudserviceapi.eo.test;

import javax.persistence.CascadeType;
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
 * ScreenLearningStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "screen_learning_style", catalog = "eol2")
public class ScreenLearningStyle implements java.io.Serializable {

	// Fields

	private Long id;
	private CdLearningStyle cdLearningStyle;
	private Screen screen;
	private Integer priority;

	// Constructors

	/** default constructor */
	public ScreenLearningStyle() {
	}

	/** minimal constructor */
	public ScreenLearningStyle(CdLearningStyle cdLearningStyle, Screen screen) {
		this.cdLearningStyle = cdLearningStyle;
		this.screen = screen;
	}

	/** full constructor */
	public ScreenLearningStyle(CdLearningStyle cdLearningStyle, Screen screen,
			Integer priority) {
		this.cdLearningStyle = cdLearningStyle;
		this.screen = screen;
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

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "LEARNING_STYLE_ID", nullable = false)
	public CdLearningStyle getCdLearningStyle() {
		return this.cdLearningStyle;
	}

	public void setCdLearningStyle(CdLearningStyle cdLearningStyle) {
		this.cdLearningStyle = cdLearningStyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCREEN_ID", nullable = false)
	public Screen getScreen() {
		return this.screen;
	}

	public void setScreen(Screen screen) {
		
		//screen.setS
		this.screen = screen;
	}

	@Column(name = "PRIORITY")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}