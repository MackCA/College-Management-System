package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class MiniFacultyCourseBag implements Serializable {
	private MiniFacultyCourseInfo[] miniFacultyCourseBagArray;
	private int nElems;
	private Random random = new Random();

	public MiniFacultyCourseInfo[] getMiniFacultyCourseInfo() {
		return miniFacultyCourseBagArray;
	}

	public int getnElms() {
		return nElems;
	}

	public MiniFacultyCourseBag(int maxSize) {
		super();
		this.miniFacultyCourseBagArray = new MiniFacultyCourseInfo[maxSize];
		this.nElems = 0;
	}

	public void insert(MiniFacultyCourseInfo miniFacultyCourseInfo) {
		miniFacultyCourseBagArray[nElems++] = miniFacultyCourseInfo;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniFacultyCourseBagArray[i]);
		}
	}

	public MiniFacultyCourseInfo findByCourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (miniFacultyCourseBagArray[i].getCourseNumber().equals(courseNumber)) {
				return miniFacultyCourseBagArray[i];
			}
		}
		return null;
	}

	public MiniFacultyCourseInfo deleteByCourseNumber(String courseNumber) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (miniFacultyCourseBagArray[i].getCourseNumber().equals(courseNumber)) {
				break;
			}
		}
		if (i == nElems) {
			System.out.println("Course number not found");
			return null;
		} else {
			MiniFacultyCourseInfo temp = miniFacultyCourseBagArray[i];
			for (int j = i; j < nElems - 1; j++) {
				miniFacultyCourseBagArray[j] = miniFacultyCourseBagArray[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public MiniFacultyCourseInfo[] findCoursesTaught() {
		//
		// int numOfTaugtCourses = 0;
		//
		// for (int i = 0; i < nElems; i++) {
		// if
		// (miniFacultyCourseBagArray[i].getFacultyCourseStatus().equals(FacultyCourseStatus.TAUGHT))
		// {
		// numOfTaugtCourses++;
		// }
		// }
		//
		// MiniFacultyCourseInfo[] taughtCourses = new
		// MiniFacultyCourseInfo[numOfTaugtCourses];
		//
		// int indexCounter = 0;
		//
		// for (int i = 0; i < nElems; i++) {
		// if
		// (miniFacultyCourseBagArray[i].getFacultyCourseStatus().equals(FacultyCourseStatus.TAUGHT))
		// {
		// taughtCourses[indexCounter++] = miniFacultyCourseBagArray[i];
		// }
		// }
		//
		// return taughtCourses;

		MiniFacultyCourseInfo[] check = new MiniFacultyCourseInfo[nElems];
		MiniFacultyCourseInfo[] coursesTaught;
		int arr = 0;
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (miniFacultyCourseBagArray[i].getFacultyCourseStatus().equals(FacultyCourseStatus.TAUGHT)) {
				check[i] = miniFacultyCourseBagArray[i];
				count++;
			}
		}
		coursesTaught = new MiniFacultyCourseInfo[count];
		for (int i = 0; i < nElems; i++) {
			if (check[i] != null) {
				coursesTaught[arr] = check[i];
				arr++;
			}
		}
		return coursesTaught;
	}

	public MiniFacultyCourseInfo[] findCoursesTeaching() {
		MiniFacultyCourseInfo[] check = new MiniFacultyCourseInfo[nElems];
		MiniFacultyCourseInfo[] coursesTeaching;
		int arr = 0;
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if (miniFacultyCourseBagArray[i].getFacultyCourseStatus().equals(FacultyCourseStatus.TEACHING)) {
				check[i] = miniFacultyCourseBagArray[i];
				count++;
			}
		}
		coursesTeaching = new MiniFacultyCourseInfo[count];
		for (int i = 0; i < nElems; i++) {
			if (check[i] != null) {
				coursesTeaching[arr] = check[i];
				arr++;
			}
		}
		return coursesTeaching;
	}

	public MiniFacultyCourseInfo emitFacultyCourseInfo() {
		return miniFacultyCourseBagArray[random.nextInt(nElems)];
	}

	public int getnElems() {
		return nElems;
	}

	@Override
	public String toString() {
		return "\n" + Arrays.toString(miniFacultyCourseBagArray) + "\n";
	}

}
