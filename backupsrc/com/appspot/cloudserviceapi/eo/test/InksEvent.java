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
 * InksEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inks_event", catalog = "eol2")
public class InksEvent implements java.io.Serializable {

	// Fields

	private Long id;
	private CdEventType cdEventType;
	private String actor;
	private String verb;
	private String object;
	private String rawData;
	private String location;
	private Long topicId;
	private Set<InksNodeExpectedEvent> inksNodeExpectedEvents = new HashSet<InksNodeExpectedEvent>(
			0);
	private Set<Mistake> mistakes = new HashSet<Mistake>(0);
	private Set<InksNodeErrorReason> inksNodeErrorReasons = new HashSet<InksNodeErrorReason>(
			0);

	// Constructors

	/** default constructor */
	public InksEvent() {
	}

	/** minimal constructor */
	public InksEvent(CdEventType cdEventType, String actor, String verb) {
		this.cdEventType = cdEventType;
		this.actor = actor;
		this.verb = verb;
	}

	/** full constructor */
	public InksEvent(CdEventType cdEventType, String actor, String verb,
			String object, String rawData, String location, Long topicId,
			Set<InksNodeExpectedEvent> inksNodeExpectedEvents,
			Set<Mistake> mistakes, Set<InksNodeErrorReason> inksNodeErrorReasons) {
		this.cdEventType = cdEventType;
		this.actor = actor;
		this.verb = verb;
		this.object = object;
		this.rawData = rawData;
		this.location = location;
		this.topicId = topicId;
		this.inksNodeExpectedEvents = inksNodeExpectedEvents;
		this.mistakes = mistakes;
		this.inksNodeErrorReasons = inksNodeErrorReasons;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_EVENT_TYPE_ID", nullable = false)
	public CdEventType getCdEventType() {
		return this.cdEventType;
	}

	public void setCdEventType(CdEventType cdEventType) {
		this.cdEventType = cdEventType;
	}

	@Column(name = "ACTOR", nullable = false, length = 250)
	public String getActor() {
		return this.actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	@Column(name = "VERB", nullable = false, length = 250)
	public String getVerb() {
		return this.verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	@Column(name = "OBJECT", length = 250)
	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Column(name = "RAW_DATA")
	public String getRawData() {
		return this.rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	@Column(name = "LOCATION", length = 250)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "TOPIC_ID")
	public Long getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksEvent")
	public Set<InksNodeExpectedEvent> getInksNodeExpectedEvents() {
		return this.inksNodeExpectedEvents;
	}

	public void setInksNodeExpectedEvents(
			Set<InksNodeExpectedEvent> inksNodeExpectedEvents) {
		this.inksNodeExpectedEvents = inksNodeExpectedEvents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksEvent")
	public Set<Mistake> getMistakes() {
		return this.mistakes;
	}

	public void setMistakes(Set<Mistake> mistakes) {
		this.mistakes = mistakes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inksEvent")
	public Set<InksNodeErrorReason> getInksNodeErrorReasons() {
		return this.inksNodeErrorReasons;
	}

	public void setInksNodeErrorReasons(
			Set<InksNodeErrorReason> inksNodeErrorReasons) {
		this.inksNodeErrorReasons = inksNodeErrorReasons;
	}

}