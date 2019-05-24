package model;

import java.io.Serializable;

public class MiniStudentInfo implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -1792943293565450185L;
	private String courseNumber;
	private String courseTitle;
	private double numberOfCredits;
	private LetterGrade letterGrade;
	private CourseStatus courseStatus;
	private String courseDescription;


	public MiniStudentInfo(String courseNumber, String courseTitle,String courseDescription, LetterGrade letterGrade,double numberOfCredits,
			CourseStatus courseStatus) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle= courseTitle;
		this.numberOfCredits = numberOfCredits;
		this.letterGrade = letterGrade;
		this.courseStatus = courseStatus;
		this.courseDescription = courseDescription;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public LetterGrade getLetterGrade() {
		return letterGrade;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setLetterGrade(LetterGrade letterGrade) {
		this.letterGrade = letterGrade;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	@Override
	public String toString() {
		return  courseNumber + "		" + numberOfCredits + "	cr.  \n"
				+ courseTitle + "\nGrade: " + letterGrade + "		" + courseStatus + "\n_________________________";
	}




}


