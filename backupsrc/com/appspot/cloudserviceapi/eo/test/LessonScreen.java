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
import javax.persistence.UniqueConstraint;

/**
 * LessonScreen entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lesson_screen", catalog = "eol2", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LESSON_ID", "SCREEN_ID" }))
public class LessonScreen implements java.io.Serializable {

	// Fields

	private Long id;
	private Screen screen;
	private Lesson lesson;
	private Integer sortOrder;

	// Constructors

	/** default constructor */
	public LessonScreen() {
	}

	/** minimal constructor */
	public LessonScreen(Screen screen, Lesson lesson) {
		this.screen = screen;
		this.lesson = lesson;
	}

	/** full constructor */
	public LessonScreen(Screen screen, Lesson lesson, Integer sortOrder) {
		this.screen = screen;
		this.lesson = lesson;
		this.sortOrder = sortOrder;
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
	@JoinColumn(name = "SCREEN_ID", nullable = false)
	public Screen getScreen() {
		return this.screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LESSON_ID", nullable = false)
	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}