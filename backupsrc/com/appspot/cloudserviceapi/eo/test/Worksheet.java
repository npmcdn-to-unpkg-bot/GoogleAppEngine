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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Worksheet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "worksheet", catalog = "eol2")
public class Worksheet implements java.io.Serializable {

	// Fields

	private Long id;
	private Long contentTypeId;
	private String name;
	private String description;
	private String externalUrl;
	private Set<Screen> screens = new HashSet<Screen>(0);
	private Set<WorksheetLearningStyle> worksheetLearningStyles = new HashSet<WorksheetLearningStyle>(
			0);

	// Constructors

	/** default constructor */
	public Worksheet() {
	}

	/** full constructor */
	public Worksheet(Long contentTypeId, String name, String description,
			String externalUrl, Set<Screen> screens,
			Set<WorksheetLearningStyle> worksheetLearningStyles) {
		this.contentTypeId = contentTypeId;
		this.name = name;
		this.description = description;
		this.externalUrl = externalUrl;
		this.screens = screens;
		this.worksheetLearningStyles = worksheetLearningStyles;
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

	@Column(name = "CONTENT_TYPE_ID")
	public Long getContentTypeId() {
		return this.contentTypeId;
	}

	public void setContentTypeId(Long contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	@Column(name = "NAME", length = 100)
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

	@Column(name = "EXTERNAL_URL", length = 500)
	public String getExternalUrl() {
		return this.externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worksheet")
	public Set<Screen> getScreens() {
		return this.screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worksheet")
	public Set<WorksheetLearningStyle> getWorksheetLearningStyles() {
		return this.worksheetLearningStyles;
	}

	public void setWorksheetLearningStyles(
			Set<WorksheetLearningStyle> worksheetLearningStyles) {
		this.worksheetLearningStyles = worksheetLearningStyles;
	}

}