package com.appspot.cloudserviceapi.eo.test;

/*import tapp.model.eo.Activity;
import tapp.model.eo.Student;
import tapp.model.eo.College;
import tapp.model.eo.CdActivityDetails;
import com.appspot.cloudserviceapi.eo.data.ActivityName;
import com.appspot.cloudserviceapi.eo.data.ActivityDetails;
import com.appspot.cloudserviceapi.eo.data.CollegeName;
import com.appspot.cloudserviceapi.eo.data.CollegeRank;
import com.appspot.cloudserviceapi.eo.dao.ActivityDAO;
import com.appspot.cloudserviceapi.eo.dao.CollegeDAO;
*///import tapp.model.eo.*;
//import com.appspot.cloudserviceapi.eo.data.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class EOPrgrmGoalDaoTest extends TestCase {
    ActivityDAO activityDAO = null;
    CollegeDAO collegeDAO = null;
    
	public void setUp() throws Exception {
		File f = new File(".");
		String rootDir = null;
		String fs = System.getProperty("file.separator");
		try {
			rootDir = f.getCanonicalPath();
			ClassPathUpdater.add(rootDir);
			ClassPathUpdater.add(rootDir + fs + "bin");
			ClassPathUpdater.add(rootDir + fs + "lib" + fs + "dom4j-1.6.1.jar");
			ClassPathUpdater.add(rootDir + fs + "lib" + fs + "commons-collections-3.2.1.jar");
			ClassPathUpdater.add(rootDir + fs + "lib" + fs + "javassist-3.8.0.GA.jar");
			ClassPathUpdater.add(rootDir + fs + "lib" + fs + "mysql-connector-java-5.1.10-bin.jar");
			ClassPathUpdater.add(rootDir + fs + "lib" + fs + "hibernate-annotations-3.2.1.ga.jar");
			ClassPathUpdater.add(rootDir + fs + "war" + fs + "WEB-INF" + fs + "lib" + fs + "slf4j-api-1.5.6.jar");
			ClassPathUpdater.add(rootDir + fs + "war" + fs + "WEB-INF" + fs + "lib" + fs + "slf4j-log4j12-1.5.2.jar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    ClassPathXmlApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			activityDAO = (ActivityDAO)ctx.getBean("activityDAO");
			collegeDAO = (CollegeDAO)ctx.getBean("collegeDAO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testPrgmGoalsRecommendedActivitySave() {
	    try {
			Activity act = null;
			CdActivityDetails actD = null;
			act = new Activity();
			act.setName(ActivityName.REGULAR_TUTORING);
			actD = new CdActivityDetails();
			actD.setDetails(ActivityDetails.ONE_HOUR_A_WEEK);
			act.setCdActivityDetails(actD);
			Student stu = new Student();
			stu.setGrade(70);
			stu.setProfileId(1L);
			College col = new College();
//			col.setId(1L);
			col.setCollegeName(CollegeName.Brown_University);
			col.setRank(CollegeRank.IVY_LEAGUE_OR_TOP_50);
			collegeDAO.save(col);
			act.setStudent(stu);
			act.setCollege(col);
			activityDAO.save(act);
			act = new Activity();
			act.setName(ActivityName.INTENSIVE_TUTORING);
			actD = new CdActivityDetails();
			actD.setDetails(ActivityDetails.FOUR_ONE_HOUR_SESSIONS_A_WEEK);
			act.setCdActivityDetails(actD);
			activityDAO.save(act);
		} catch (BeansException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
	
/*	public void testPTInputPrgmGoalsParen tTrgtSave() {
		//dependent class
		Profile profile = new Profile();
		profile.setProfileId(new Long(1));
		profile.setAddedBy("12");
		profile.setProfileFname("Jennifer");
		profile.setProfileId(new Long(1));
		profile.setProfileLname("Tan");
		profile.setProfileType('S');
		profile.setUpdtBy("1");
		profile.setUpdtDt(new Date());
		profile.setAccountId(new Long(1));
		dao.save(profile);

		CdLearningStyle cdLearningStyle = new CdLearningStyle();
		Long id = new Long(1);
		cdLearningStyle.setCdLearningStyleId(id);
		cdLearningStyle.setLearningStyleName("learningStyleName");
		dao.save(cdLearningStyle);
		
		StudentLearningStyle studentLearningStyle = new StudentLearningStyle();
		StudentLearningStylePK pk = new StudentLearningStylePK();
		pk.setStudentId(new Long(1)); //id has to exist in profile table and must be student (S), created previously
		pk.setLearningStyleId(new Long(1)); //id has to exist in cd_lrng_style table (created previously)
		studentLearningStyle.setStudentLearningStylePK(pk);	//EmbeddedId/Embeddable approach does not work somehow
//		studentLearningStyle.setStudentId(new Long(1));
//		studentLearningStyle.setLearningStyleId(new Long(1));
		studentLearningStyle.setStudentLearningStyleId(new Long(1));	//does not matter what id it is anymore
		studentLearningStyle.setWeight(new Long(1));
		studentLearningStyle.setSubjectId(new Long(2));
		studentLearningStyle.setAddedBy("addedBy");
		studentLearningStyle.setUpdtBy("updtBy");
		dao.save(studentLearningStyle, pk);
//		dao.save(studentLearningStyle);

		Student student = new Student();
		student.setStudentId(new Long(1));
		student.setProfileId(new Long(1));
		student.setGrade(new Integer(8));
		dao.save(student);

		Teacher teacher = new Teacher();
		teacher.setTeacherId(new Long(1));	
		dao.save(teacher);

		PrgmGoalsParentTrgt prgmGoalsParentTrgt = new PrgmGoalsParentTrgt();
		prgmGoalsParentTrgt.setPrgmGoalsParentTrgtId(new Long(1));
		prgmGoalsParentTrgt.setRespondentId(new Long(4));
		prgmGoalsParentTrgt.setGradePctgTrgt(new BigDecimal(1));
		prgmGoalsParentTrgt.setGradePctgAvg(new BigDecimal(2));
		prgmGoalsParentTrgt.setGradePctgLastYr(new BigDecimal(3));
		prgmGoalsParentTrgt.setCollege("YES");
		prgmGoalsParentTrgt.setRespondentDt(new Date());
		dao.save(prgmGoalsParentTrgt);
	}

	public void testPTInputPrgmGoalsRespondentSave() {
		PrgmGoalsRespondent prgmGoalsRespondent = new PrgmGoalsRespondent();
		prgmGoalsRespondent.setPrgmGoalsRespondentId(new Long(1));
		prgmGoalsRespondent.setRespondentDt(new Date());
		prgmGoalsRespondent.setRespondentType("1");
		prgmGoalsRespondent.setStudentId(new Long(2));
		prgmGoalsRespondent.setTeacherId(new Long(14));
		prgmGoalsRespondent.setSubjectId(new Long(1));
		prgmGoalsRespondent.setStudentRank(new BigDecimal(1));
		prgmGoalsRespondent.setGradePctg(new BigDecimal(1));
		prgmGoalsRespondent.setAddedDt(new Date());
		prgmGoalsRespondent.setAddedBy("1");
		prgmGoalsRespondent.setUpdtDt(new Date());
		prgmGoalsRespondent.setUpdtBy("1");
		dao.save(prgmGoalsRespondent);
	}
	
	public void testSaveAll() { 
		CdActy cdActy = new CdActy();
		cdActy.setActyName("actyName");
		cdActy.setCdActyId(new Long(1));
		dao.save(cdActy);

		CdAcvtyDetls cdAcvtyDetls = new CdAcvtyDetls();
		cdAcvtyDetls.setCdAcvtyDetlsId(new Long(1));
		cdAcvtyDetls.setAcvtyDetlsName("acvtyDetlsName");
		dao.save(cdAcvtyDetls);

		CdAsmntDetls cdAsmntDetls = new CdAsmntDetls();
		cdAsmntDetls.setCdAsmntDetlsId(new Long(1));
		cdAsmntDetls.setAsmntDetlsName("asmntDetlsName");
		dao.save(cdAsmntDetls);
		
		CdCollegeType cdCollegeType = new CdCollegeType();
		cdCollegeType.setCdCollegeTypeId(new Long(1));
		cdCollegeType.setCollegeTypeDesc("collegeTypeDesc");
		cdCollegeType.setCollegeGpa(new BigDecimal(1));
		dao.save(cdCollegeType);
		
		CdLearningObjective cdLearningObjective = new CdLearningObjective();
		cdLearningObjective.setName("name");
		cdLearningObjective.setTopicId(new Long(1));
		cdLearningObjective.setCdLearningObjectiveId(new Long(1));
		dao.save(cdLearningObjective);
				
		CdSubject cdSubject = new CdSubject();
		cdSubject.setCdSubjectId(new Long(1));
		cdSubject.setSubjectName("Math");
		dao.save(cdSubject);
		
		CdTopic cdTopic = new CdTopic();
		cdTopic.setCdTopicId(new Long(1));
		cdTopic.setSubjectId(new Long(1));
		cdTopic.setTopicName("topicName");
		dao.save(cdTopic);
		
		Goal goal = new Goal();
		goal.setGoalId(new Long(1));
		goal.setStudentId(new Long(1));
		goal.setTopicId(new Long(1));
		dao.save(goal);
		
		PrgmGoalsAcvtySched prgmGoalsAcvtySched = new PrgmGoalsAcvtySched();
		prgmGoalsAcvtySched.setPrgmGoalsAcvtySchedId(new Long(5));
		prgmGoalsAcvtySched.setRespondentId(new Long(1));
		prgmGoalsAcvtySched.setAcvtyDt(new Date());
		prgmGoalsAcvtySched.setAcvtyDetlsId(new Long(1));
		prgmGoalsAcvtySched.setAsmntId('J');		
		dao.save(prgmGoalsAcvtySched);
		
		PrgmGoalsAppldKnwlg prgmGoalsAppldKnwlg = new PrgmGoalsAppldKnwlg();
		prgmGoalsAppldKnwlg.setAppliesKnowledgeRealWorld(Short.valueOf("1"));
		prgmGoalsAppldKnwlg.setPrgmGoalsAppldKnwlgId(new Long(1));
		prgmGoalsAppldKnwlg.setSolvesIntegratedProblems(Short.valueOf("3"));
		dao.save(prgmGoalsAppldKnwlg);
		
		PrgmGoalsAsmntRmind prgmGoalsAsmntRmind = new PrgmGoalsAsmntRmind();
		prgmGoalsAsmntRmind.setPrgmGoalsAsmntRmindId(new Long(1));
		prgmGoalsAsmntRmind.setRmindType("rmindT");
		prgmGoalsAsmntRmind.setRmindEmailId("rmindEmailId");
		dao.save(prgmGoalsAsmntRmind);
		
		PrgmGoalsAsmntSched prgmGoalsAsmntSched = new PrgmGoalsAsmntSched();
		prgmGoalsAsmntSched.setPrgmGoalsAsmntSchedId(new Long(1));
		prgmGoalsAsmntSched.setRespondentId(new Long(1));
		prgmGoalsAsmntSched.setAsmntDt(new Date());
		prgmGoalsAsmntSched.setAsmntDetlsId(new Long(1));
		dao.save(prgmGoalsAsmntSched);
		
		PrgmGoalsGradeScale prgmGoalsGradeScale = new PrgmGoalsGradeScale();
		prgmGoalsGradeScale.setPrgmGoalsGradeScaleId(new Long(1));
		prgmGoalsGradeScale.setGradeLowerBnd(new BigDecimal(0.5));
		prgmGoalsGradeScale.setGradeUpperBnd(new BigDecimal(1));
		prgmGoalsGradeScale.setRespondentId(new Long(1));
		prgmGoalsGradeScale.setGradeScaleId("A+");
		dao.save(prgmGoalsGradeScale);
		
		PrgmGoalsLearningStyle prgmGoalsLearningStyle = new PrgmGoalsLearningStyle();
		prgmGoalsLearningStyle.setPrgmGoalsLearningStyleId(new Long(1));
		prgmGoalsLearningStyle.setAnyStyle(Short.valueOf("2"));
		prgmGoalsLearningStyle.setRealWorldExamples(Short.valueOf("6"));
		prgmGoalsLearningStyle.setSimplerExplanations(Short.valueOf("1"));
		prgmGoalsLearningStyle.setVisual(Short.valueOf("3"));
		prgmGoalsLearningStyle.setAuditory(Short.valueOf("4"));
		prgmGoalsLearningStyle.setHandsOn(Short.valueOf("5"));
		prgmGoalsLearningStyle.setDeductive(Short.valueOf("5"));		
		prgmGoalsLearningStyle.setInductive(Short.valueOf("5"));
		dao.save(prgmGoalsLearningStyle);
				
		PrgmGoalsPrgmSched prgmGoalsPrgmSched = new PrgmGoalsPrgmSched();
		prgmGoalsPrgmSched.setPrgmGoalsPrgmSchedId(new Long(1));
		prgmGoalsPrgmSched.setSubjectId(new Long(1));
		prgmGoalsPrgmSched.setSchedEndTm(new Date());
		prgmGoalsPrgmSched.setSchedBgnTm(new Date());
		prgmGoalsPrgmSched.setSchedDay("MON");
		prgmGoalsPrgmSched.setRespondentId(new Long(4));
		dao.save(prgmGoalsPrgmSched);
		
		PrgmGoalsRegistration prgmGoalsRegistration = new PrgmGoalsRegistration();
		prgmGoalsRegistration.setPrgmGoalsRegistrationId(new Long(4));
		prgmGoalsRegistration.setAddedBy("addedBy");
		prgmGoalsRegistration.setAddedDt(new Date());
		prgmGoalsRegistration.setUpdtBy("updtBy");
		prgmGoalsRegistration.setUpdtDt(new Date());
		dao.save(prgmGoalsRegistration);
				
		PrgmGoalsSkillSets prgmGoalsSkillSets = new PrgmGoalsSkillSets();
		prgmGoalsSkillSets.setPrgmGoalsSkillSetsId(new Long(1));
		prgmGoalsSkillSets.setComputationAccuracy(Short.valueOf("7"));
		prgmGoalsSkillSets.setCalculatorUse(Short.valueOf("1"));
		prgmGoalsSkillSets.setRetention(Short.valueOf("1"));
		prgmGoalsSkillSets.setFormulaSelection(Short.valueOf("1"));
		prgmGoalsSkillSets.setAbstractConceptGrasp(Short.valueOf("1"));
		prgmGoalsSkillSets.setLogicalThoughtProcess(Short.valueOf("1"));
		prgmGoalsSkillSets.setLowProblemProficiency(Short.valueOf("1"));
		prgmGoalsSkillSets.setMedProblemProficiency(Short.valueOf("1"));
		prgmGoalsSkillSets.setHiProblemProficiency(Short.valueOf("1"));
		prgmGoalsSkillSets.setSnglStepProblemSkill(Short.valueOf("1"));
		prgmGoalsSkillSets.setMultStepProblemSkill(Short.valueOf("1"));
		dao.save(prgmGoalsSkillSets);
		
		PrgmGoalsWorkHbits prgmGoalsWorkHbits = new PrgmGoalsWorkHbits();
		prgmGoalsWorkHbits.setPrgmGoalsWorkHbitsId(new Long(1));
		prgmGoalsWorkHbits.setCompletesAssignments(Short.valueOf("1"));
		prgmGoalsWorkHbits.setShowsSolution(Short.valueOf("1"));
		prgmGoalsWorkHbits.setFocus(Short.valueOf("1"));
		prgmGoalsWorkHbits.setAccuracy(Short.valueOf("1"));
		prgmGoalsWorkHbits.setOrganization(Short.valueOf("1"));
		prgmGoalsWorkHbits.setPreparation(Short.valueOf("1"));
		dao.save(prgmGoalsWorkHbits);
		
		Profile profile = new Profile();
		profile.setProfileId(new Long(1));
		profile.setAddedBy("12");
		profile.setProfileFname("1");
		profile.setProfileId(new Long(1));
		profile.setProfileLname("1");
		profile.setProfileType('1');
		profile.setUpdtBy("1");
		profile.setUpdtDt(new Date());
		profile.setAccountId(new Long(1));
		dao.save(profile);
		
		ProfileDetails profileDetails = new ProfileDetails();
		profileDetails.setProfileDetailsId(new Long(1));
		profileDetails.setProfileId(new Long(1));
		profileDetails.setSessnLngthMin(Byte.parseByte("1"));
		profileDetails.setSessnLngthMax(Byte.parseByte("1"));
		profileDetails.setGrade("1");
		profileDetails.setAddedBy("1");
		profileDetails.setUpdtBy("1");
		profileDetails.setUpdtDt(new Date());
		dao.save(profileDetails);
		
		Score score = new Score();
		score.setScoreId(new Long(1));
		score.setStudentId(new Long(1));
		score.setTopicId(new Long(1));
		score.setTimestamp(new Date());
		dao.save(score);
		
		StudentActivityLog studentActivityLog = new StudentActivityLog();
		studentActivityLog.setStudentActivityLogId(new Long(1));
		studentActivityLog.setStudentId(new Long(1));
		studentActivityLog.setStartTm(new Date());
		studentActivityLog.setEndTm(new Date());
		studentActivityLog.setActivityId(new Long(1));
		studentActivityLog.setActivityType('1');
		studentActivityLog.setSubjectId(new Long(1));
		studentActivityLog.setTopicId(new Long(1));
		studentActivityLog.setLrnObjId(new Long(1));
		studentActivityLog.setSessionId(new Long(1));
		studentActivityLog.setIndicator("1");
		studentActivityLog.setOtherId(new Long(1));
		studentActivityLog.setAddedDt(new Date());
		studentActivityLog.setAddedBy("1");		
		dao.save(studentActivityLog);
		
		StudentParentPair studentParentPair = new StudentParentPair();
		studentParentPair.setStudentParentPairId(new Long(1));
		studentParentPair.setStudentId(new Long(1));
		studentParentPair.setParentId(new Long(1));
		studentParentPair.setAddedDt(new Date());
		studentParentPair.setAddedBy("1");
		studentParentPair.setUpdtDt(new Date());
		studentParentPair.setUpdtBy("1");		
		dao.save(studentParentPair);
		
		StudentResults studentResults = new StudentResults();
		studentResults.setStudentResultsId(new Long(1));
		studentResults.setStudentId(new Long(1));
		studentResults.setSubjectId(new Long(1));
		studentResults.setActivityId(new Long(1));
		studentResults.setActivityType('1');
		studentResults.setStartTm(new Date());
		studentResults.setEndTm(new Date());
		studentResults.setAddedDt(new Date());
		studentResults.setAddedBy("1");
		studentResults.setUpdtDt(new Date());
		studentResults.setUpdtBy("1");		
		dao.save(studentResults);
		
		StudentTeacherPair studentTeacherPair = new StudentTeacherPair();
		studentTeacherPair.setStudentTeacherPairId(new Long(1));
		studentTeacherPair.setTeacherId(new Long(1));
		studentTeacherPair.setStudentId(new Long(1));
		studentTeacherPair.setSubjectId(new Long(1));
		studentTeacherPair.setAddedDt(new Date());
		studentTeacherPair.setAddedBy("1");
		studentTeacherPair.setUpdtDt(new Date());
		studentTeacherPair.setUpdtBy("1");		
		dao.save(studentTeacherPair);
		
		Subject subject = new Subject();
		subject.setSubjectId(new Long(1));
		subject.setSubjectName("subjectName");
		dao.save(subject);
				
		Topic topic = new Topic();
		topic.setTopicId(new Long(1));
		topic.setSubjectId(new Long(1));
		dao.save(topic);
	}
*/
	/*
	public void testFindAll() {
		List aList =  dao.findAll(CdAsmntDetls.class);
		System.out.println (">>>>CdAsmntDetls List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdActy.class);
		System.out.println (">>>>CdActy List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdAcvtyDetls.class);
		System.out.println (">>>>CdAcvtyDetls List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdCollegeType.class);
		System.out.println (">>>>CdCollegeType List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdLearningObjective.class);
		System.out.println (">>>>CdLearningObjective List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdLearningStyle.class);
		System.out.println (">>>>CdLearningStyle List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdSubject.class);
		System.out.println (">>>>CdSubject List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(CdTopic.class);
		System.out.println (">>>>CdTopic List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Goal.class);
		System.out.println (">>>>Goal List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsAcvtySched.class);
		System.out.println (">>>>PrgmGoalsAcvtySched List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsAppldKnwlg.class);
		System.out.println (">>>>PrgmGoalsAppldKnwlg List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsAsmntRmind.class);
		System.out.println (">>>>PrgmGoalsAsmntRmind List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsAsmntSched.class);
		System.out.println (">>>>PrgmGoalsAsmntSched List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsGradeScale.class);
		System.out.println (">>>>PrgmGoalsGradeScale List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsLearningStyle.class);
		System.out.println (">>>>PrgmGoalsLearningStyle List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsParentTrgt.class);
		System.out.println (">>>>PrgmGoalsParentTrgt List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsPrgmSched.class);
		System.out.println (">>>>PrgmGoalsPrgmSched List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsRegistration.class);
		System.out.println (">>>>PrgmGoalsRegistration List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsRespondent.class);
		System.out.println (">>>>PrgmGoalsRespondent List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsSkillSets.class);
		System.out.println (">>>>PrgmGoalsSkillSets List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(PrgmGoalsWorkHbits.class);
		System.out.println (">>>>PrgmGoalsWorkHbits List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Profile.class);
		System.out.println (">>>>Profile List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(ProfileDetails.class);
		System.out.println (">>>>ProfileDetails List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Score.class);
		System.out.println (">>>>Score List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Student.class);
		System.out.println (">>>>Student List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(StudentActivityLog.class);
		System.out.println (">>>>StudentActivityLog List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(StudentLearningStyle.class);
		System.out.println (">>>>StudentLearningStyle List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(StudentParentPair.class);
		System.out.println (">>>>StudentParentPair List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(StudentResults.class);
		System.out.println (">>>>StudentResults List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(StudentTeacherPair.class);
		System.out.println (">>>>StudentTeacherPair List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Subject.class);
		System.out.println (">>>>Subject List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Teacher.class);
		System.out.println (">>>>Teacher List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));

		aList =  dao.findAll(Topic.class);
		System.out.println (">>>>Topic List(0): " + ((aList == null || aList.size() == 0)?null:aList.get(0)));
	}
	*/
	
/*	public void testFindProfile() {
		Profile p = null;
		p = (Profile) dao.find(Profile.class, 2);
		System.out.println (">>>>Profile: " + p);		
		assertNotNull(p);
	}

	public void testFindAllProfile() {
		BaseDao dao = new BaseDao();
		List<Profile> profileList = null; 
		profileList = dao.findAll(Profile.class);
		for(Profile p:profileList) {
			System.out.println(p.getFullName());
		}
		assertNotNull(profileList);
	}
*/}
