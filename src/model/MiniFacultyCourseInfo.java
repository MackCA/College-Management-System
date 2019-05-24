package model;

import java.io.Serializable;

public class MiniFacultyCourseInfo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -4474640815143251913L;
	private String courseNumber;
	private String courseTitle;
	private double numberOfCredits;
	private FacultyCourseStatus facultyCourseStatus;

	public MiniFacultyCourseInfo(String courseNumber, String courseTitle, String courseDescription,
			double numberOfCredits, FacultyCourseStatus facultyCourseStatus) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.numberOfCredits = numberOfCredits;
		this.facultyCourseStatus = facultyCourseStatus;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public void setFacultyCourseStatus(FacultyCourseStatus facultyCourseStatus) {
		this.facultyCourseStatus = facultyCourseStatus;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public FacultyCourseStatus getFacultyCourseStatus() {
		return facultyCourseStatus;
	}

	@Override
	public String toString() {
		return courseNumber + "		" + numberOfCredits+ "cr.	Status: " + facultyCourseStatus
				+ "	\n" + courseTitle+"\n_________________________";
	}

}
