package com.appspot.cloudserviceapi.eo.test;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AssessmentItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment_item", catalog = "eol2")
public class AssessmentItem implements java.io.Serializable {

	// Fields

	private Long id;
	private Screen screen;
	private CdQuestionType cdQuestionType;
	private LearningObjective learningObjective;
	private String name;
	private String questionText;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String choice5;
	private String answers;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<AssessmentDetail> assessmentDetails = new HashSet<AssessmentDetail>(
			0);

	// Constructors

	/** default constructor */
	public AssessmentItem() {
	}

	/** minimal constructor */
	public AssessmentItem(Screen screen, CdQuestionType cdQuestionType,
			LearningObjective learningObjective, String name,
			String questionText, String answers, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy) {
		this.screen = screen;
		this.cdQuestionType = cdQuestionType;
		this.learningObjective = learningObjective;
		this.name = name;
		this.questionText = questionText;
		this.answers = answers;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public AssessmentItem(Screen screen, CdQuestionType cdQuestionType,
			LearningObjective learningObjective, String name,
			String questionText, String choice1, String choice2,
			String choice3, String choice4, String choice5, String answers,
			Date addedDt, Long addedBy, Date updatedDt, Long updatedBy,
			Set<AssessmentDetail> assessmentDetails) {
		this.screen = screen;
		this.cdQuestionType = cdQuestionType;
		this.learningObjective = learningObjective;
		this.name = name;
		this.questionText = questionText;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.choice5 = choice5;
		this.answers = answers;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.assessmentDetails = assessmentDetails;
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
	@JoinColumn(name = "QUESTION_TYPE_ID", nullable = false)
	public CdQuestionType getCdQuestionType() {
		return this.cdQuestionType;
	}

	public void setCdQuestionType(CdQuestionType cdQuestionType) {
		this.cdQuestionType = cdQuestionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LO_ID", nullable = false)
	public LearningObjective getLearningObjective() {
		return this.learningObjective;
	}

	public void setLearningObjective(LearningObjective learningObjective) {
		this.learningObjective = learningObjective;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "QUESTION_TEXT", nullable = false, length = 500)
	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Column(name = "CHOICE_1", length = 100)
	public String getChoice1() {
		return this.choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	@Column(name = "CHOICE_2", length = 100)
	public String getChoice2() {
		return this.choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	@Column(name = "CHOICE_3", length = 100)
	public String getChoice3() {
		return this.choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	@Column(name = "CHOICE_4", length = 100)
	public String getChoice4() {
		return this.choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	@Column(name = "CHOICE_5", length = 100)
	public String getChoice5() {
		return this.choice5;
	}

	public void setChoice5(String choice5) {
		this.choice5 = choice5;
	}

	@Column(name = "ANSWERS", nullable = false, length = 100)
	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADDED_DT", nullable = false, length = 10)
	public Date getAddedDt() {
		return this.addedDt;
	}

	public void setAddedDt(Date addedDt) {
		this.addedDt = addedDt;
	}

	@Column(name = "ADDED_BY", nullable = false)
	public Long getAddedBy() {
		return this.addedBy;
	}

	public void setAddedBy(Long addedBy) {
		this.addedBy = addedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DT", nullable = false, length = 10)
	public Date getUpdatedDt() {
		return this.updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@Column(name = "UPDATED_BY", nullable = false)
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessmentItem")
	public Set<AssessmentDetail> getAssessmentDetails() {
		return this.assessmentDetails;
	}

	public void setAssessmentDetails(Set<AssessmentDetail> assessmentDetails) {
		this.assessmentDetails = assessmentDetails;
	}

}