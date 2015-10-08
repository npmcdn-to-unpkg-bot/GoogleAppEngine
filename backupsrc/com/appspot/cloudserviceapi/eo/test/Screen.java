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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Screen entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "screen", catalog = "eol2")

@SqlResultSetMapping(name="screen", entities=@EntityResult(entityClass=Screen.class))
/*
@NamedNativeQuery(name="screen_for_loid",
		query="select s.id, s.name, s.lo_id, s.sort_order, s.answers, s.worksheet_id, s.instruction_id," +
				"s.access_scope_id, s.cd_screen_type_id, s.inks_tree_id, s.cd_problem_type_id, cd_instruction_type_id," +
				"s.explanation_id, s.graphic_id, s.grade_start, s.grade_end, s.eol_rating, s.inks_attr," +
				"s.required_tools, s.student_rating, s.eol_rating, s.teacher_rating, s.graphic_attr, s.explanation_attr," +
				"s.instruction_attr, s.worksheet_attr, s.is_require_answer, s.td_id " +
				" from screen s, screen_learning_style scls, " +
				"student_learning_style sls where s.lo_id=:loId " +
				"and scls.screen_id = s.id " +
				"and sls.learning_style_id = scls.learning_style_id " +
				"and sls.profile_id = :profileId" , resultSetMapping="screen")
*/

@NamedNativeQueries({
	/*For use case 3*/
	@NamedNativeQuery(name="screen_for_loid",
			query="select s.* from screen s, screen_learning_style scls, " +
					"student_learning_style sls where s.lo_id=:loId " +
					"and scls.screen_id = s.id " +
					"and s.cd_screen_type_id=:screenTypeId " +
					"and sls.learning_style_id = scls.learning_style_id " +
					"and sls.profile_id = :profileId " +
					"order by s.id, scls.priority, s.sort_order" , resultSetMapping="screen"),
					
	@NamedNativeQuery(name="screen_for_pp",
			query="select s.* from screen s, screen_learning_style scls, " +
					"student_learning_style sls where s.lo_id=:loId " +
					"and scls.screen_id = s.id " +
					"and s.cd_screen_type_id=:screenTypeId " +
					"and sls.learning_style_id = scls.learning_style_id " +
					"and sls.profile_id = :profileId and s.inks_tree_id is not null " +
					"order by s.id, scls.priority, s.sort_order" , resultSetMapping="screen"),				
					/*For use case 5*/
	@NamedNativeQuery(name = "screen_for_loid_sort_order",
			query = "select s.* from screen s, screen_learning_style scls, "
				+ "student_learning_style sls where s.lo_id=:loId "
				+ "and scls.screen_id = s.id "
				+ "and sls.learning_style_id = scls.learning_style_id "
				+ "and sls.profile_id = :profileId "
				+ "and s.sort_order>:currentSortOrder order by scls.priority, s.sort_order", resultSetMapping = "screen"),
				/*For use case 6*/
	@NamedNativeQuery(name="screen_for_loid_priority",
			query="select s.* from screen s, screen_learning_style scls, " +
					"student_learning_style sls where s.lo_id=:loId " +
					"and scls.screen_id = s.id " +
					"and sls.learning_style_id = scls.learning_style_id " +
					"and sls.profile_id = :profileId " +
					"and scls.priority>:currentPriority order by scls.priority, s.sort_order" , resultSetMapping="screen"),
				/*For counting the total number of practice problems for a learning objective*/	
	@NamedNativeQuery(name="screenlist_for_lo",
			query="select s.* from screen s where s.lo_id=:loId and cd_screen_type_id = 4" , resultSetMapping="screen")
					
	}
)
public class Screen implements java.io.Serializable {

	// Fields

	private Long id;
	private CdAccessScope cdAccessScope;
	private CdScreenType cdScreenType;
	private InksTree inksTree;
	private Explanation explanation;
	private CdProblemType cdProblemType;
	private CdInstructionType cdInstructionType;
	private Instruction instruction;
	private Worksheet worksheet;
	private LearningObjective learningObjective;
	private Graphic graphic;
	private String name;
	private Integer gradeStart;
	private Integer gradeEnd;
	private Integer sortOrder;
	private String answers;
	private String inksAttr;
	private String requiredTools;
	private Float studentRating;
	private Float teacherRating;
	private Float eolRating;
	private String graphicAttr;
	private String explanationAttr;
	private String worksheetAttr;
	private String instructionAttr;
	private String isRequireAnswer;
	private Long tdId;
	private Set<AssessmentItem> assessmentItems = new HashSet<AssessmentItem>(0);
	private Set<ScreenLearningStyle> screenLearningStyles = new HashSet<ScreenLearningStyle>(
			0);
	private Set<LessonScreen> lessonScreens = new HashSet<LessonScreen>(0);
	private Set<RatingScreen> ratingScreens = new HashSet<RatingScreen>(0);

	// Constructors

	/** default constructor */
	public Screen() {
	}

	/** minimal constructor */
	public Screen(LearningObjective learningObjective, String name) {
		this.learningObjective = learningObjective;
		this.name = name;
	}

	/** full constructor */
	public Screen(CdAccessScope cdAccessScope, CdScreenType cdScreenType,
			InksTree inksTree, Explanation explanation,
			CdProblemType cdProblemType, CdInstructionType cdInstructionType,
			Instruction instruction, Worksheet worksheet,
			LearningObjective learningObjective, Graphic graphic, String name,
			Integer gradeStart, Integer gradeEnd, Integer sortOrder,
			String answers, String inksAttr, String requiredTools,
			Float studentRating, Float teacherRating, Float eolRating,
			String graphicAttr, String explanationAttr, String worksheetAttr,
			String instructionAttr, String isRequireAnswer, Long tdId,
			Set<AssessmentItem> assessmentItems,
			Set<ScreenLearningStyle> screenLearningStyles,
			Set<LessonScreen> lessonScreens, Set<RatingScreen> ratingScreens) {
		this.cdAccessScope = cdAccessScope;
		this.cdScreenType = cdScreenType;
		this.inksTree = inksTree;
		this.explanation = explanation;
		this.cdProblemType = cdProblemType;
		this.cdInstructionType = cdInstructionType;
		this.instruction = instruction;
		this.worksheet = worksheet;
		this.learningObjective = learningObjective;
		this.graphic = graphic;
		this.name = name;
		this.gradeStart = gradeStart;
		this.gradeEnd = gradeEnd;
		this.sortOrder = sortOrder;
		this.answers = answers;
		this.inksAttr = inksAttr;
		this.requiredTools = requiredTools;
		this.studentRating = studentRating;
		this.teacherRating = teacherRating;
		this.eolRating = eolRating;
		this.graphicAttr = graphicAttr;
		this.explanationAttr = explanationAttr;
		this.worksheetAttr = worksheetAttr;
		this.instructionAttr = instructionAttr;
		this.isRequireAnswer = isRequireAnswer;
		this.tdId = tdId;
		this.assessmentItems = assessmentItems;
		this.screenLearningStyles = screenLearningStyles;
		this.lessonScreens = lessonScreens;
		this.ratingScreens = ratingScreens;
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
	@JoinColumn(name = "ACCESS_SCOPE_ID")
	public CdAccessScope getCdAccessScope() {
		return this.cdAccessScope;
	}

	public void setCdAccessScope(CdAccessScope cdAccessScope) {
		this.cdAccessScope = cdAccessScope;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_SCREEN_TYPE_ID")
	public CdScreenType getCdScreenType() {
		return this.cdScreenType;
	}

	public void setCdScreenType(CdScreenType cdScreenType) {
		this.cdScreenType = cdScreenType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "INKS_TREE_ID")
	public InksTree getInksTree() {
		return this.inksTree;
	}

	public void setInksTree(InksTree inksTree) {
		this.inksTree = inksTree;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EXPLANATION_ID")
	public Explanation getExplanation() {
		return this.explanation;
	}

	public void setExplanation(Explanation explanation) {
		this.explanation = explanation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_PROBLEM_TYPE_ID")
	public CdProblemType getCdProblemType() {
		return this.cdProblemType;
	}

	public void setCdProblemType(CdProblemType cdProblemType) {
		this.cdProblemType = cdProblemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_INSTRUCTION_TYPE_ID")
	public CdInstructionType getCdInstructionType() {
		return this.cdInstructionType;
	}

	public void setCdInstructionType(CdInstructionType cdInstructionType) {
		this.cdInstructionType = cdInstructionType;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "INSTRUCTION_ID")
	public Instruction getInstruction() {
		return this.instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "WORKSHEET_ID")
	public Worksheet getWorksheet() {
		return this.worksheet;
	}



	public void setWorksheet(Worksheet worksheet) {
		this.worksheet = worksheet;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LO_ID", nullable = false)
	public LearningObjective getLearningObjective() {
		return this.learningObjective;
	}

	public void setLearningObjective(LearningObjective learningObjective) {
		this.learningObjective = learningObjective;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRAPHIC_ID")
	public Graphic getGraphic() {
		return this.graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GRADE_START")
	public Integer getGradeStart() {
		return this.gradeStart;
	}

	public void setGradeStart(Integer gradeStart) {
		this.gradeStart = gradeStart;
	}

	@Column(name = "GRADE_END")
	public Integer getGradeEnd() {
		return this.gradeEnd;
	}

	public void setGradeEnd(Integer gradeEnd) {
		this.gradeEnd = gradeEnd;
	}

	@Column(name = "SORT_ORDER")
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "ANSWERS", length = 100)
	public String getAnswers() {
		return this.answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	@Column(name = "INKS_ATTR", length = 250)
	public String getInksAttr() {
		return this.inksAttr;
	}

	public void setInksAttr(String inksAttr) {
		this.inksAttr = inksAttr;
	}

	@Column(name = "REQUIRED_TOOLS", length = 1)
	public String getRequiredTools() {
		return this.requiredTools;
	}

	public void setRequiredTools(String requiredTools) {
		this.requiredTools = requiredTools;
	}

	@Column(name = "STUDENT_RATING", precision = 12, scale = 0)
	public Float getStudentRating() {
		return this.studentRating;
	}

	public void setStudentRating(Float studentRating) {
		this.studentRating = studentRating;
	}

	@Column(name = "TEACHER_RATING", precision = 12, scale = 0)
	public Float getTeacherRating() {
		return this.teacherRating;
	}

	public void setTeacherRating(Float teacherRating) {
		this.teacherRating = teacherRating;
	}

	@Column(name = "EOL_RATING", precision = 12, scale = 0)
	public Float getEolRating() {
		return this.eolRating;
	}

	public void setEolRating(Float eolRating) {
		this.eolRating = eolRating;
	}

	@Column(name = "GRAPHIC_ATTR", length = 250)
	public String getGraphicAttr() {
		return this.graphicAttr;
	}

	public void setGraphicAttr(String graphicAttr) {
		this.graphicAttr = graphicAttr;
	}

	@Column(name = "EXPLANATION_ATTR", length = 250)
	public String getExplanationAttr() {
		return this.explanationAttr;
	}

	public void setExplanationAttr(String explanationAttr) {
		this.explanationAttr = explanationAttr;
	}

	@Column(name = "WORKSHEET_ATTR", length = 250)
	public String getWorksheetAttr() {
		return this.worksheetAttr;
	}

	public void setWorksheetAttr(String worksheetAttr) {
		this.worksheetAttr = worksheetAttr;
	}

	@Column(name = "INSTRUCTION_ATTR", length = 250)
	public String getInstructionAttr() {
		return this.instructionAttr;
	}

	public void setInstructionAttr(String instructionAttr) {
		this.instructionAttr = instructionAttr;
	}

	@Column(name = "IS_REQUIRE_ANSWER", length = 1)
	public String getIsRequireAnswer() {
		return this.isRequireAnswer;
	}

	public void setIsRequireAnswer(String isRequireAnswer) {
		this.isRequireAnswer = isRequireAnswer;
	}

	@Column(name = "TD_ID")
	public Long getTdId() {
		return this.tdId;
	}

	public void setTdId(Long tdId) {
		this.tdId = tdId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "screen")
	public Set<AssessmentItem> getAssessmentItems() {
		return this.assessmentItems;
	}

	public void setAssessmentItems(Set<AssessmentItem> assessmentItems) {
		this.assessmentItems = assessmentItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "screen")
	public Set<ScreenLearningStyle> getScreenLearningStyles() {
		return this.screenLearningStyles;
	}

	public void setScreenLearningStyles(
			Set<ScreenLearningStyle> screenLearningStyles) {
		this.screenLearningStyles = screenLearningStyles;

		for (ScreenLearningStyle sls : screenLearningStyles)
		{
			sls.setScreen(this);
		}
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "screen")
	public Set<LessonScreen> getLessonScreens() {
		return this.lessonScreens;
	}

	public void setLessonScreens(Set<LessonScreen> lessonScreens) {
		this.lessonScreens = lessonScreens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "screen")
	public Set<RatingScreen> getRatingScreens() {
		return this.ratingScreens;
	}

	public void setRatingScreens(Set<RatingScreen> ratingScreens) {
		this.ratingScreens = ratingScreens;
	}

}