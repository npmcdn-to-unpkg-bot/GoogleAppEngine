package com.appspot.cloudserviceapi.eo.test;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CdGraphicType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_graphic_type", catalog = "eol2")
public class CdGraphicType implements java.io.Serializable {

	// Fields

	private Long id;
	private Topic topic;
	private String name;
	private Long topicId;
	private Integer sortOrder;
	private Set<GraphicTypeLearningStyle> graphicTypeLearningStyles = new HashSet<GraphicTypeLearningStyle>(
			0);
	private Set<Graphic> graphics = new HashSet<Graphic>(0);

	// Constructors

	/** default constructor */
	public CdGraphicType() {
	}

	/** minimal constructor */
	public CdGraphicType(Topic topic, String name) {
		this.topic = topic;
		this.name = name;
	}

	/** full constructor */
	public CdGraphicType(Topic topic, String name, Long topicId,
			Integer sortOrder,
			Set<GraphicTypeLearningStyle> graphicTypeLearningStyles,
			Set<Graphic> graphics) {
		this.topic = topic;
		this.name = name;
		this.topicId = topicId;
		this.sortOrder = sortOrder;
		this.graphicTypeLearningStyles = graphicTypeLearningStyles;
		this.graphics = graphics;
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
	@JoinColumn(name = "ID", unique = true, nullable = false, insertable = false, updatable = false)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdGraphicType")
	public Set<GraphicTypeLearningStyle> getGraphicTypeLearningStyles() {
		return this.graphicTypeLearningStyles;
	}

	public void setGraphicTypeLearningStyles(
			Set<GraphicTypeLearningStyle> graphicTypeLearningStyles) {
		this.graphicTypeLearningStyles = graphicTypeLearningStyles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdGraphicType")
	public Set<Graphic> getGraphics() {
		return this.graphics;
	}

	public void setGraphics(Set<Graphic> graphics) {
		this.graphics = graphics;
	}

}