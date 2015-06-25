package com.appspot.cloudserviceapi.eo.test;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ParentInput implements Cloneable, Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    //private Long id;	//can't be Long due to GAE/J JPA child limitation (error: Cannot have a java.lang.Long primary key and be a child object)
    private Key id;
	@Basic
	Long studentId;
	@Basic
	Subject subject;
	@Basic
	Grade desiredPerformance;
	@Basic
	String collegeChoice;
	@Basic
	String learningStyle;
	@Basic
	String workHabit;
	@Basic
	String skillSetAndLearningCurve;
	@Basic
	String intAndAppOfKnowledge;
	@Basic
	boolean solicitInformationFromTeacher;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Grade getDesiredPerformance() {
		return desiredPerformance;
	}

	public void setDesiredPerformance(Grade desiredPerformance) {
		this.desiredPerformance = desiredPerformance;
	}

	public String getCollegeChoice() {
		return collegeChoice;
	}

	public void setCollegeChoice(String collegeChoice) {
		this.collegeChoice = collegeChoice;
	}

	public String getLearningStyle() {
		return learningStyle;
	}

	public void setLearningStyle(String learningStyle) {
		this.learningStyle = learningStyle;
	}

	public String getWorkHabit() {
		return workHabit;
	}

	public void setWorkHabit(String workHabit) {
		this.workHabit = workHabit;
	}

	public String getSkillSetAndLearningCurve() {
		return skillSetAndLearningCurve;
	}

	public void setSkillSetAndLearningCurve(String skillSetAndlearningCurve) {
		this.skillSetAndLearningCurve = skillSetAndlearningCurve;
	}

	public String getIntAndAppOfKnowledge() {
		return intAndAppOfKnowledge;
	}

	public void setIntAndAppOfKnowledge(
			String IntAndAppOfKnowledge) {
		this.intAndAppOfKnowledge = IntAndAppOfKnowledge;
	}

	public boolean isSolicitInformationFromTeacher() {
		return solicitInformationFromTeacher;
	}

	public void setSolicitInformationFromTeacher(
			boolean solicitInformationFromTeacher) {
		this.solicitInformationFromTeacher = solicitInformationFromTeacher;
	}

	public Object clone() throws CloneNotSupportedException {
		ParentInput clone = (ParentInput)super.clone();
		return clone;
	}
	
}
