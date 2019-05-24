package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.ClassroomBag;
import model.College;
import model.CourseBag;
import model.PersonBag;
import model.TextbookBag;

public class Utils {
public static void restore(College college) {
		restoreClassroomBag(college.getClassroomBag(),college);
		restoreCourseBag(college.getCourseBag(),college);
		restoreTextbookBag(college.getTextbookBag(),college);
		restorePersonBag(college.getPersonBag(),college);
		restoreIdCounter();
	}

	public static void backup(ClassroomBag classroomBag, CourseBag courseBag, TextbookBag textbookBag,PersonBag personBag,College college) {
		backupClassroomBag(classroomBag,college);
		backupCourseBag(courseBag);
		backupTextbookBag(textbookBag);
		backupPersonBag(personBag);
		backupIdCounter();
	}

	public static void backupClassroomBag(ClassroomBag classroomBag,College college) {
			try {
				FileOutputStream fos = new FileOutputStream("backups\\classroomBag.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(college.getClassroomBag());
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	public static void backupCourseBag(CourseBag courseBag) {

			try {
				FileOutputStream fos = new FileOutputStream("backups\\courseBag.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(courseBag);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static void backupTextbookBag(TextbookBag textbookBag) {

		try {
			FileOutputStream fos = new FileOutputStream("backups\\textbookBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(textbookBag);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void backupIdCounter() {

		try {
			FileOutputStream fos = new FileOutputStream("backups\\idCounter.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(IdFactory.getIdCounter());
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void backupPersonBag(PersonBag personBag) {

		try {
			FileOutputStream fos = new FileOutputStream("backups\\personBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(personBag);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void restoreClassroomBag(ClassroomBag classroomBag,College college) {

		File file = new File("backups\\classroomBag.dat");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream("backups\\classroomBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setClassroomBag((ClassroomBag) (ois.readObject()));
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void restoreCourseBag(CourseBag courseBag,College college) {
		File file = new File("backups\\courseBag.dat");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream("backups\\courseBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setCourseBag((CourseBag) (ois.readObject()));
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void restoreTextbookBag(TextbookBag textbookBag,College college) {
		File file = new File("backups\\textbookBag.dat");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream("backups\\textbookBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				 college.setTextbookBag((TextbookBag) (ois.readObject()));
				 ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	public static void restorePersonBag(PersonBag personBag,College college) {
		File file = new File("backups\\personBag.dat");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream("backups\\personBag.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				college.setPersonBag((PersonBag) (ois.readObject()));
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void restoreIdCounter() {

		File file = new File("backups\\idCounter.dat");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream("backups\\idCounter.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				IdFactory.setIdCounter((Integer) (ois.readObject()));
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}