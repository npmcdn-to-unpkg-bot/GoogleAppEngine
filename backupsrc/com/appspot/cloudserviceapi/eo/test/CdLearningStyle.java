package com.appspot.cloudserviceapi.eo.test;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CdLearningStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cd_learning_style", catalog = "eol2")

@SqlResultSetMapping(name="cd_learning_style", entities=@EntityResult(entityClass=CdLearningStyle.class))
@NamedNativeQueries({
	/*Does this help*/
	@NamedNativeQuery(name="lsStyle", query="select ls.* from cd_learning_style ls", resultSetMapping="cd_learning_style"
)

}
)


public class CdLearningStyle implements java.io.Serializable {

	// Fields

	private Long id;
	private String shortName;
	private String fullName;
	private Integer sortOrder;
	private Set<StudentLearningStyle> studentLearningStyles = new HashSet<StudentLearningStyle>(
			0);
	private Set<GraphicTypeLearningStyle> graphicTypeLearningStyles = new HashSet<GraphicTypeLearningStyle>(
			0);
	private Set<WorksheetLearningStyle> worksheetLearningStyles = new HashSet<WorksheetLearningStyle>(
			0);
	private Set<ScreenLearningStyle> screenLearningStyles = new HashSet<ScreenLearningStyle>(
			0);

	// Constructors

	/** default constructor */
	public CdLearningStyle() {
	}

	/** minimal constructor */
	public CdLearningStyle(String shortName, String fullName) {
		this.shortName = shortName;
		this.fullName = fullName;
	}

	/** full constructor */
	public CdLearningStyle(String shortName, String fullName,
			Integer sortOrder, Set<StudentLearningStyle> studentLearningStyles,
			Set<GraphicTypeLearningStyle> graphicTypeLearningStyles,
			Set<WorksheetLearningStyle> worksheetLearningStyles,
			Set<ScreenLearningStyle> screenLearningStyles) {
		this.shortName = shortName;
		this.fullName = fullName;
		this.sortOrder = sortOrder;
		this.studentLearningStyles = studentLearningStyles;
		this.graphicTypeLearningStyles = graphicTypeLearningStyles;
		this.worksheetLearningStyles = worksheetLearningStyles;
		this.screenLearningStyles = screenLearningStyles;
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

	@Column(name = "SHORT_NAME", nullable = false, length = 25)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "FULL_NAME", nullable = false, length = 250)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdLearningStyle")
	public Set<StudentLearningStyle> getStudentLearningStyles() {
		return this.studentLearningStyles;
	}

	public void setStudentLearningStyles(
			Set<StudentLearningStyle> studentLearningStyles) {
		this.studentLearningStyles = studentLearningStyles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdLearningStyle")
	public Set<GraphicTypeLearningStyle> getGraphicTypeLearningStyles() {
		return this.graphicTypeLearningStyles;
	}

	public void setGraphicTypeLearningStyles(
			Set<GraphicTypeLearningStyle> graphicTypeLearningStyles) {
		this.graphicTypeLearningStyles = graphicTypeLearningStyles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdLearningStyle")
	public Set<WorksheetLearningStyle> getWorksheetLearningStyles() {
		return this.worksheetLearningStyles;
	}

	public void setWorksheetLearningStyles(
			Set<WorksheetLearningStyle> worksheetLearningStyles) {
		this.worksheetLearningStyles = worksheetLearningStyles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cdLearningStyle")
	public Set<ScreenLearningStyle> getScreenLearningStyles() {
		return this.screenLearningStyles;
	}

	public void setScreenLearningStyles(
			Set<ScreenLearningStyle> screenLearningStyles) {
		this.screenLearningStyles = screenLearningStyles;
	}

}