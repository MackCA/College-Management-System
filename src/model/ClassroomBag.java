package model;

import java.io.Serializable;
import java.util.Random;

public class ClassroomBag implements Serializable{
	/**
	 * 
	 */

	private Classroom[] classroomArray;
	private  int nElems;
	private Random random = new Random();

	public ClassroomBag(int maxSize) {
		super();
		classroomArray = new Classroom[maxSize];
		this.nElems = 0;
	}

	public void insert(Classroom classroom) {
		classroomArray[nElems++] = classroom;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(classroomArray[i]);
		}
	}

	public Classroom findByRoomNumber(String roomNumber) {
		int i=0;

		for (i = 0; i < nElems; i++) {
			if (classroomArray[i].getRoomNumber().equals(roomNumber)) {
				return classroomArray[i];
			}
		}
		return null;
	}

	public Classroom deleteByRoomNumber(String roomNumber) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (classroomArray[i].getRoomNumber().equals(roomNumber)) {
				break;
			}
		}
		if (i == nElems) {
			System.out.println("Room number not found.");
			return null;
		} else {
			Classroom temp = classroomArray[i];
			for (int j = i; j < nElems - 1; j++) {
				classroomArray[j] = classroomArray[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public Classroom[] getClassroomArray() {
		return classroomArray;
	}

	public int getnElems() {
		return nElems;
	}

	public Classroom emitClassroom() {
		return classroomArray[random.nextInt(nElems)];
	}
}
