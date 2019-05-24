package model;

import java.io.Serializable;
import java.util.Random;

public class Course implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7997851540325697563L;
	Random random = new Random();
	private String courseNumber;
	private String courseTitle;
	private String courseDescription;
	private double numberOfCredits;

	public Course(String courseNumber, String courseTitle, String courseDescription, double numberOfCredits) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.numberOfCredits = numberOfCredits;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	@Override
	public String toString() {
		return courseNumber +"    "+ numberOfCredits+"Cr. \n" +"Course Title: " + courseTitle + "\nDescription: "
				+ courseDescription + "\n_____________________________";
	}


}
