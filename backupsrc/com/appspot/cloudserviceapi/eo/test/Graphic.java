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
 * Graphic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "graphic", catalog = "eol2")
public class Graphic implements java.io.Serializable {

	// Fields

	private Long id;
	private CdGraphicType cdGraphicType;
	private String name;
	private String description;
	private String locationUrl;
	private Set<Screen> screens = new HashSet<Screen>(0);

	// Constructors

	/** default constructor */
	public Graphic() {
	}

	/** minimal constructor */
	public Graphic(String name) {
		this.name = name;
	}

	/** full constructor */
	public Graphic(CdGraphicType cdGraphicType, String name,
			String description, String locationUrl, Set<Screen> screens) {
		this.cdGraphicType = cdGraphicType;
		this.name = name;
		this.description = description;
		this.locationUrl = locationUrl;
		this.screens = screens;
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
	@JoinColumn(name = "CD_GRAPHIC_TYPE_ID")
	public CdGraphicType getCdGraphicType() {
		return this.cdGraphicType;
	}

	public void setCdGraphicType(CdGraphicType cdGraphicType) {
		this.cdGraphicType = cdGraphicType;
	}

	@Column(name = "NAME", nullable = false, length = 100)
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

	@Column(name = "LOCATION_URL", length = 500)
	public String getLocationUrl() {
		return this.locationUrl;
	}

	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "graphic")
	public Set<Screen> getScreens() {
		return this.screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

}