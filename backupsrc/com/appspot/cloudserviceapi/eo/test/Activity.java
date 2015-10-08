package com.appspot.cloudserviceapi.eo.test;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Activity implements Cloneable, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private ActivityName name = ActivityName.REGULAR_TUTORING;
	private boolean hasShortAttentionSpan;

	@Transient
	private String details;
	@Transient
	private Long studentId;
	@Transient
	private Integer studentGrade;
	@Transient
	private Grade desiredPerformance = Grade.A;
	@Transient
	private String studentPerformance;
	@Transient
	private int performanceGap;
	private String subjectName;

    @NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private ParentInput parentInput; // this is just for test, in reality there
										// will be more than one object

    @NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private CdActivityDetails cdActivityDetails;

	@OneToOne(cascade = CascadeType.ALL)
	private College college;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Subject subject;

	@NotNull
	@OneToOne
	private ProgramGoalsParentTarget parentTarget;

	@NotNull
	//can't operate on multiple entity groups in a single transaction. found both Element { type: "Activity" id: 70844 } and Element { type: "Student" id: 70848 }
//    @OneToOne(optional=false, mappedBy="activity")
//    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name="PROFILE_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		Long retVal = -1L;
		if(student != null) {
			retVal = student.getProfileId();
		}
		return retVal;
	}
	
	private Integer getStudentGrade() {
		Integer retVal = -1;
		if(student != null) {
			retVal = student.getGrade();
		}
		return retVal;
	}
	
	public String getStudentPerformance() {
		return "" + toGrade(getStudentGrade()) + " (" + getStudentGrade() + ")";
	}

	public void setStudentPerformance(String studentPerformance) {
		this.studentPerformance = studentPerformance;
	}
	
	public int getPerformanceGap() {
		return getGapCount();
	}

	public void setPerformanceGap(int performanceGap) {
		this.performanceGap = performanceGap;
	}

	public Grade getDesiredPerformance() {
		desiredPerformance = null;

		if (parentInput != null) {
			desiredPerformance = parentInput.getDesiredPerformance();
		}

		return desiredPerformance;
	}

	public void setDesiredPerformance(Grade performance) {
	}

	public String getSubjectName() {
		if (subject != null) {
			subjectName = subject.getName();
		}

		return subjectName;
	}

	@Validate(value = "required")
	@NotNull
	public void setSubjectName(String subjectName) {
		if (subject != null) {
			subject.setName(subjectName);
		}
	}

	public ActivityName getName() {
		return name;
	}

	@NotNull @Size(max=45)
	public void setName(ActivityName name) {
		this.name = name;
	}
	
	public boolean isHasShortAttentionSpan() {
		return hasShortAttentionSpan;
	}

	public void setHasShortAttentionSpan(boolean hasShortAttentionSpan) {
		this.hasShortAttentionSpan = hasShortAttentionSpan;
	}

	public String getDetails() {
		details = "";

		if (cdActivityDetails != null && cdActivityDetails.getDetails() != null) {
			details = cdActivityDetails.getDetails().toString();
		}

		return details;
	}

	public void setDetails(String details) {
	}

	public CdActivityDetails getCdActivityDetails() {
		return cdActivityDetails;
	}

	@NotNull
	public void setCdActivityDetails(CdActivityDetails cdActivityDetails) {
		this.cdActivityDetails = cdActivityDetails;
	}

	public ParentInput getParentInput() {
		return parentInput;
	}

	@NotNull
	public void setParentInput(ParentInput parentInput) {
		this.parentInput = parentInput;
	}
	
	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

/*	public SubjectName getSubjectName() {
		return subjectName;
	}

	@Validate(value = "required")
	@NotNull
	public void setSubjectName(SubjectName subject) {
		this.subjectName = subjectName;
	}
*/	
	public ProgramGoalsParentTarget getParentTarget() {
		return parentTarget;
	}

	public void setParentTarget(ProgramGoalsParentTarget parentTarget) {
		this.parentTarget = parentTarget;
	}

	private Grade toGrade(Integer grade) {
		Grade retVal = Grade.F;
		if (grade >= 90 && grade <= 100) {
			retVal = Grade.A;
		} else if (grade >= 80 && grade <= 89) {
			retVal = Grade.B;
		} else if (grade >= 70 && grade <= 79) {
			retVal = Grade.C;
		} else if (grade >= 60 && grade <= 69) {
			retVal = Grade.D;
		} else if (grade >= 0 && grade <= 59) {
			retVal = Grade.F;
		}
		return retVal;
	}

	private int toGradeLevel(Integer grade) {
		int retVal = 1;
		if (grade >= 90 && grade <= 100) {
			retVal = 5;
		} else if (grade >= 80 && grade <= 89) {
			retVal = 4;
		} else if (grade >= 70 && grade <= 79) {
			retVal = 3;
		} else if (grade >= 60 && grade <= 69) {
			retVal = 2;
		} else if (grade >= 0 && grade <= 59) {
			retVal = 1;
		}
		return retVal;
	}

	private int toGradeLevel(Grade grade) {
		int retVal = 1;
		if (grade == Grade.A) {
			retVal = 5;
		} else if (grade == Grade.B) {
			retVal = 4;
		} else if (grade == Grade.C) {
			retVal = 3;
		} else if (grade == Grade.D) {
			retVal = 2;
		} else if (grade == Grade.F) {
			retVal = 1;
		}
		return retVal;
	}

	private int getGapCount() {
		int retVal = -1;
		if(desiredPerformance != null && getStudentGrade() != null) {
			retVal = toGradeLevel(desiredPerformance) - toGradeLevel(getStudentGrade());
		}
		return retVal;
	}
	
	public void handleActivity() {
		if(getGapCount() >= 2) {
			name = ActivityName.INTENSIVE_TUTORING;
			if(!hasShortAttentionSpan) {
				cdActivityDetails.setDetails(ActivityDetails.FOUR_ONE_HOUR_SESSIONS_A_WEEK);
			} else {
				cdActivityDetails.setDetails(ActivityDetails.EIGHT_30_MINUTES_SESSIONS_A_WEEK);
			}
		} else if(getGapCount() == 1) {
			name = ActivityName.REGULAR_TUTORING;
		} else if(getGapCount() <= 0) {
			name = ActivityName.TUTORING_IS_OPTIONAL;
		}
	}
	
	public Object clone() throws CloneNotSupportedException {
		Activity clone = (Activity) super.clone();

		return clone;
	}

}