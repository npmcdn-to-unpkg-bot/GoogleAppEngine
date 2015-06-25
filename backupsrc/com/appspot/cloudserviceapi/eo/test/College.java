package com.appspot.cloudserviceapi.eo.test;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "college", catalog = "eol2")
public class College implements Cloneable, Serializable {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private CollegeName collegeName;
	private CollegeRank rank;
	@Transient
	private Grade requiredGrade;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CollegeName getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(CollegeName collegeName) {
		this.collegeName = collegeName;
	}

	public CollegeRank getRank() {
		return rank;
	}

	public void setRank(CollegeRank rank) {
		this.rank = rank;
	}

	@NonVisual
	public Grade getRequiredGrade() {
		return requiredGrade;
	}

	public void setRequiredGrade(Grade requiredGrade) {
		this.requiredGrade = requiredGrade;
	}

	public Object clone() throws CloneNotSupportedException {
		College clone = (College) super.clone();

		return clone;
	}

}