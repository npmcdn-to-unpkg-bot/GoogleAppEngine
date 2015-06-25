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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Profile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profile", catalog = "eol2")
public class Profile implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String profileType;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffx;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String attention;
	private Date birthDt;
	private String email;
	private String textMessage;
	private Date addedDt;
	private Long addedBy;
	private Date updatedDt;
	private Long updatedBy;
	private Set<StudentSchoolPair> studentSchoolPairs = new HashSet<StudentSchoolPair>(
			0);
	private Set<StudentParentPair> studentParentPairsForParentId = new HashSet<StudentParentPair>(
			0);
	private Set<RatingScreen> ratingScreens = new HashSet<RatingScreen>(0);
	private Set<TeacherInput> teacherInputsForTeacherId = new HashSet<TeacherInput>(
			0);
	private Set<ProgramGoalsRespondent> programGoalsRespondentsForStudentId = new HashSet<ProgramGoalsRespondent>(
			0);
	private Set<ProgramGoalsRespondent> programGoalsRespondentsForTeacherId = new HashSet<ProgramGoalsRespondent>(
			0);
	private Set<ProgramGoalsSchedule> programGoalsSchedules = new HashSet<ProgramGoalsSchedule>(
			0);
	private Set<StudentResults> studentResultses = new HashSet<StudentResults>(
			0);
	private Set<TeacherInput> teacherInputsForStudentId = new HashSet<TeacherInput>(
			0);
	private Set<StudentParentPair> studentParentPairsForStudentId = new HashSet<StudentParentPair>(
			0);
	private Set<RatingFeedback> ratingFeedbacks = new HashSet<RatingFeedback>(0);
	private Set<StudentTeacherPair> studentTeacherPairsForTeacherId = new HashSet<StudentTeacherPair>(
			0);
	private Set<StudentLearningStyle> studentLearningStyles = new HashSet<StudentLearningStyle>(
			0);
	private Set<StudentTeacherPair> studentTeacherPairsForStudentId = new HashSet<StudentTeacherPair>(
			0);
	private Set<StudentActivityLog> studentActivityLogs = new HashSet<StudentActivityLog>(
			0);
	private Set<RatingHint> ratingHints = new HashSet<RatingHint>(0);
	
	private Set<StudentLearningCompletedStats> studentLearningCompletedStats = new HashSet<StudentLearningCompletedStats>(0);
	
	private Set<StudentStandardResult> studentStandardResult = new HashSet<StudentStandardResult>(0);

	// Constructors

	/** default constructor */
	public Profile() {
	}

	/** minimal constructor */
	public Profile(Long userId, String profileType, String firstName,
			String lastName, Date addedDt, Long addedBy, Date updatedDt,
			Long updatedBy) {
		this.userId = userId;
		this.profileType = profileType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
	}

	/** full constructor */
	public Profile(Long userId, String profileType, String firstName,
			String middleName, String lastName, String suffx, String address1,
			String address2, String address3, String city, String state,
			String zip, String country, String attention, Date birthDt,
			String email, String textMessage, Date addedDt, Long addedBy,
			Date updatedDt, Long updatedBy,
			Set<StudentSchoolPair> studentSchoolPairs,
			Set<StudentParentPair> studentParentPairsForParentId,
			Set<RatingScreen> ratingScreens,
			Set<TeacherInput> teacherInputsForTeacherId,
			Set<ProgramGoalsRespondent> programGoalsRespondentsForStudentId,
			Set<ProgramGoalsRespondent> programGoalsRespondentsForTeacherId,
			Set<ProgramGoalsSchedule> programGoalsSchedules,
			Set<StudentResults> studentResultses,
			Set<TeacherInput> teacherInputsForStudentId,
			Set<StudentParentPair> studentParentPairsForStudentId,
			Set<RatingFeedback> ratingFeedbacks,
			Set<StudentTeacherPair> studentTeacherPairsForTeacherId,
			Set<StudentLearningStyle> studentLearningStyles,
			Set<StudentTeacherPair> studentTeacherPairsForStudentId,
			Set<StudentActivityLog> studentActivityLogs,
			Set<RatingHint> ratingHints,
			Set<StudentLearningCompletedStats> studentLearningCompletedStats,
			Set<StudentStandardResult> studentStandardResult) {
		this.userId = userId;
		this.profileType = profileType;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffx = suffx;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.attention = attention;
		this.birthDt = birthDt;
		this.email = email;
		this.textMessage = textMessage;
		this.addedDt = addedDt;
		this.addedBy = addedBy;
		this.updatedDt = updatedDt;
		this.updatedBy = updatedBy;
		this.studentSchoolPairs = studentSchoolPairs;
		this.studentParentPairsForParentId = studentParentPairsForParentId;
		this.ratingScreens = ratingScreens;
		this.teacherInputsForTeacherId = teacherInputsForTeacherId;
		this.programGoalsRespondentsForStudentId = programGoalsRespondentsForStudentId;
		this.programGoalsRespondentsForTeacherId = programGoalsRespondentsForTeacherId;
		this.programGoalsSchedules = programGoalsSchedules;
		this.studentResultses = studentResultses;
		this.teacherInputsForStudentId = teacherInputsForStudentId;
		this.studentParentPairsForStudentId = studentParentPairsForStudentId;
		this.ratingFeedbacks = ratingFeedbacks;
		this.studentTeacherPairsForTeacherId = studentTeacherPairsForTeacherId;
		this.studentLearningStyles = studentLearningStyles;
		this.studentTeacherPairsForStudentId = studentTeacherPairsForStudentId;
		this.studentActivityLogs = studentActivityLogs;
		this.ratingHints = ratingHints;
		this.studentLearningCompletedStats = studentLearningCompletedStats;
		this.studentStandardResult= studentStandardResult;
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

	@Column(name = "USER_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "PROFILE_TYPE", nullable = false, length = 2)
	public String getProfileType() {
		return this.profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME", length = 45)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "SUFFX", length = 4)
	public String getSuffx() {
		return this.suffx;
	}

	public void setSuffx(String suffx) {
		this.suffx = suffx;
	}

	@Column(name = "ADDRESS1", length = 80)
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "ADDRESS2", length = 80)
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "ADDRESS3", length = 80)
	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	@Column(name = "CITY", length = 25)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", length = 25)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ZIP", length = 10)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "COUNTRY", length = 25)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "ATTENTION", length = 80)
	public String getAttention() {
		return this.attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DT", length = 10)
	public Date getBirthDt() {
		return this.birthDt;
	}

	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}

	@Column(name = "EMAIL", length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "TEXT_MESSAGE", length = 15)
	public String getTextMessage() {
		return this.textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<StudentSchoolPair> getStudentSchoolPairs() {
		return this.studentSchoolPairs;
	}

	public void setStudentSchoolPairs(Set<StudentSchoolPair> studentSchoolPairs) {
		this.studentSchoolPairs = studentSchoolPairs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByParentId")
	public Set<StudentParentPair> getStudentParentPairsForParentId() {
		return this.studentParentPairsForParentId;
	}

	public void setStudentParentPairsForParentId(
			Set<StudentParentPair> studentParentPairsForParentId) {
		this.studentParentPairsForParentId = studentParentPairsForParentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<RatingScreen> getRatingScreens() {
		return this.ratingScreens;
	}

	public void setRatingScreens(Set<RatingScreen> ratingScreens) {
		this.ratingScreens = ratingScreens;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByTeacherId")
	public Set<TeacherInput> getTeacherInputsForTeacherId() {
		return this.teacherInputsForTeacherId;
	}

	public void setTeacherInputsForTeacherId(
			Set<TeacherInput> teacherInputsForTeacherId) {
		this.teacherInputsForTeacherId = teacherInputsForTeacherId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByStudentId")
	public Set<ProgramGoalsRespondent> getProgramGoalsRespondentsForStudentId() {
		return this.programGoalsRespondentsForStudentId;
	}

	public void setProgramGoalsRespondentsForStudentId(
			Set<ProgramGoalsRespondent> programGoalsRespondentsForStudentId) {
		this.programGoalsRespondentsForStudentId = programGoalsRespondentsForStudentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByTeacherId")
	public Set<ProgramGoalsRespondent> getProgramGoalsRespondentsForTeacherId() {
		return this.programGoalsRespondentsForTeacherId;
	}

	public void setProgramGoalsRespondentsForTeacherId(
			Set<ProgramGoalsRespondent> programGoalsRespondentsForTeacherId) {
		this.programGoalsRespondentsForTeacherId = programGoalsRespondentsForTeacherId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<ProgramGoalsSchedule> getProgramGoalsSchedules() {
		return this.programGoalsSchedules;
	}

	public void setProgramGoalsSchedules(
			Set<ProgramGoalsSchedule> programGoalsSchedules) {
		this.programGoalsSchedules = programGoalsSchedules;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<StudentResults> getStudentResultses() {
		return this.studentResultses;
	}

	public void setStudentResultses(Set<StudentResults> studentResultses) {
		this.studentResultses = studentResultses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByStudentId")
	public Set<TeacherInput> getTeacherInputsForStudentId() {
		return this.teacherInputsForStudentId;
	}

	public void setTeacherInputsForStudentId(
			Set<TeacherInput> teacherInputsForStudentId) {
		this.teacherInputsForStudentId = teacherInputsForStudentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByStudentId")
	public Set<StudentParentPair> getStudentParentPairsForStudentId() {
		return this.studentParentPairsForStudentId;
	}

	public void setStudentParentPairsForStudentId(
			Set<StudentParentPair> studentParentPairsForStudentId) {
		this.studentParentPairsForStudentId = studentParentPairsForStudentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<RatingFeedback> getRatingFeedbacks() {
		return this.ratingFeedbacks;
	}

	public void setRatingFeedbacks(Set<RatingFeedback> ratingFeedbacks) {
		this.ratingFeedbacks = ratingFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByTeacherId")
	public Set<StudentTeacherPair> getStudentTeacherPairsForTeacherId() {
		return this.studentTeacherPairsForTeacherId;
	}

	public void setStudentTeacherPairsForTeacherId(
			Set<StudentTeacherPair> studentTeacherPairsForTeacherId) {
		this.studentTeacherPairsForTeacherId = studentTeacherPairsForTeacherId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<StudentLearningStyle> getStudentLearningStyles() {
		return this.studentLearningStyles;
	}

	public void setStudentLearningStyles(
			Set<StudentLearningStyle> studentLearningStyles) {
		this.studentLearningStyles = studentLearningStyles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profileByStudentId")
	public Set<StudentTeacherPair> getStudentTeacherPairsForStudentId() {
		return this.studentTeacherPairsForStudentId;
	}

	public void setStudentTeacherPairsForStudentId(
			Set<StudentTeacherPair> studentTeacherPairsForStudentId) {
		this.studentTeacherPairsForStudentId = studentTeacherPairsForStudentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<StudentActivityLog> getStudentActivityLogs() {
		return this.studentActivityLogs;
	}

	public void setStudentActivityLogs(
			Set<StudentActivityLog> studentActivityLogs) {
		this.studentActivityLogs = studentActivityLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<RatingHint> getRatingHints() {
		return this.ratingHints;
	}

	public void setRatingHints(Set<RatingHint> ratingHints) {
		this.ratingHints = ratingHints;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<StudentLearningCompletedStats> getStudentLearningCompletedStats() {
		return studentLearningCompletedStats;
	}

	public void setStudentLearningCompletedStats(
			Set<StudentLearningCompletedStats> studentLearningCompletedStats) {
		this.studentLearningCompletedStats = studentLearningCompletedStats;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
	public Set<StudentStandardResult> getStudentStandardResult() {
		return studentStandardResult;
	}

	public void setStudentStandardResult(
			Set<StudentStandardResult> studentStandardResult) {
		this.studentStandardResult = studentStandardResult;
	}

}