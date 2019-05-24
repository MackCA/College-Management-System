package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class CourseBag implements Serializable {
	private Course[] courseArray;
	private int nElems;
	Random random = new Random();

	public Course[] getCourseArray() {
		Course[] courseArrayCk = new Course[nElems];
		int arr = 0;
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (courseArray[i] != null) {
				courseArrayCk[i] = courseArray[i];
				count++;
			}
		}
		courseArray = new Course[count];
		for (int i = 0; i < nElems; i++) {
			if (courseArrayCk[i] != null) {
				courseArray[arr] = courseArrayCk[i];
				arr++;
			}

		}
		return courseArray;
	}

	public int getnElems() {
		return nElems;
	}

	public CourseBag(int maxSize) {
		super();
		this.courseArray = new Course[maxSize];
		this.nElems = 0;
	}

	public void insert(Course course) {
		courseArray[nElems++] = course;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(courseArray[i]);
		}
	}

	public Course findByCourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (courseArray[i].getCourseNumber().equals(courseNumber)) {
				return courseArray[i];
			}
		}
		System.out.println("Course not found.");
		return null;
	}

	public Course deleteByCourseNumber(String courseNumber) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (courseArray[i].getCourseNumber().equals(courseNumber)) {
				break;
			}
		}
		if (i == nElems) {
			System.out.println("Course number not found.");
			return null;
		} else {
			Course temp = courseArray[i];
			for (int j = i; j < nElems - 1; j++) {
				courseArray[j] = courseArray[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public Course emitCourse() {
		return courseArray[random.nextInt(nElems)];
	}

	@Override
	public String toString() {
		return "CourseBag [courseArray=" + Arrays.toString(courseArray) + "]";
	}

}
