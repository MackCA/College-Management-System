package model;

import java.io.Serializable;
import java.util.Random;

public class MiniStudentCourseBag implements Serializable {
	private MiniStudentInfo[] miniStudentInfoArray;
	private int nElems;
	Random random = new Random();

	public MiniStudentInfo[] getMiniStudentInfo() {
		return miniStudentInfoArray;
	}

	public int getnElems() {
		return nElems;
	}

	public MiniStudentCourseBag(int maxSize) {
		super();
		this.miniStudentInfoArray = new MiniStudentInfo[maxSize];
		this.nElems = 0;
	}

	public void insert(MiniStudentInfo studentInfo) {
		miniStudentInfoArray[nElems++] = studentInfo;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniStudentInfoArray[i]);
		}
	}

	public MiniStudentInfo findByCourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (miniStudentInfoArray[i].getCourseNumber().equals(courseNumber)) {
				return miniStudentInfoArray[i];
			}
		}

		return null;
	}

	public MiniStudentInfo[] deleteByCourseNumber(String courseNumber) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (miniStudentInfoArray[i].getCourseNumber().equals(courseNumber)) {
				break;
			}
		}
		if (i == nElems) {
			return null;
		} else {
			for (int j = i; j < nElems - 1; j++) {
				miniStudentInfoArray[j] = miniStudentInfoArray[j + 1];
			}
			nElems--;
		}
		return miniStudentInfoArray;
	}

	public MiniStudentInfo[] findCoursesTaken() {
		MiniStudentInfo[] check = new MiniStudentInfo[nElems];
		MiniStudentInfo[] coursesTaken;
		int arr =0;
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (miniStudentInfoArray[i].getCourseStatus().equals(CourseStatus.TAKEN)) {
				check[i] = miniStudentInfoArray[i];
				count++;
			}
		}
		coursesTaken = new MiniStudentInfo[count];
		for (int i = 0; i < nElems; i++) {
				if (check[i] != null) {
					coursesTaken[arr] = check[i];
					arr++;
				}
		}
		return coursesTaken;
	}

	public MiniStudentInfo[] findCoursesTaking() {
		MiniStudentInfo[] check = new MiniStudentInfo[nElems];
		MiniStudentInfo[] coursesTaking;
		int count = 0;
		int arr=0;
		for (int i = 0; i < nElems; i++) {
			if (miniStudentInfoArray[i].getCourseStatus().equals(CourseStatus.TAKING)) {
				check[i] = miniStudentInfoArray[i];
				count++;
			}
		}
		coursesTaking = new MiniStudentInfo[count];
		for (int i = 0; i < nElems; i++) {
				if (check[i] != null) {
					coursesTaking[arr] = check[i];
					arr++;
				}
		}
		return coursesTaking;
	}

	public MiniStudentInfo[] findCoursesToTake() {
		MiniStudentInfo[] check = new MiniStudentInfo[nElems];
		MiniStudentInfo[] coursesToTake;
		int arr = 0;
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (miniStudentInfoArray[i].getCourseStatus().equals(CourseStatus.TOTAKE)) {
				check[i] = miniStudentInfoArray[i];
				count++;
			}
		}
		coursesToTake = new MiniStudentInfo[count];
		for (int i = 0; i < nElems; i++) {
				if (check[i] != null) {
					coursesToTake[arr] = check[i];
					arr++;
				}
		}
		return coursesToTake;
	}

	public MiniStudentInfo emitStudentCourseInfo() {
		return miniStudentInfoArray[random.nextInt(nElems)];
	}

	public MiniStudentInfo[] getMiniStudentInfoArray() {
		return miniStudentInfoArray;
	}







}
