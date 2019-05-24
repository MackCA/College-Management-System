package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import model.Address;
import model.Faculty;
import model.Title;
import model.ValueException;

public class FacultyFactory {
	static Random random = new Random();
	private static model.PersonBag bag;

	public FacultyFactory(model.PersonBag bag) {
		super();
		FacultyFactory.bag = bag;
	}

	public static model.Faculty generateFaculty() throws IOException, ValueException{
		FileWriter fw = new FileWriter("data\\export\\Faculty List.txt",true);
		PrintWriter output = new PrintWriter(fw);
		Faculty faculty = null;
		String firstName = NameFactory.emitFirstName();
		String lastName = NameFactory.emitLastName();
		model.Department department = emitDepartment();
		Title title = emitTitle();
		double salary = emitSalary();
		String phone = ContactInfoFactory.emitPhone();
		Address address = ContactInfoFactory.emitAddress();
		model.MiniFacultyCourseBag miniFacultyCourseBag = MiniFacultyCourseFactory.emitMiniFacultyCourseBag();
		faculty = new model.Faculty(firstName, lastName,phone, address, title, salary, department,miniFacultyCourseBag);
		output.println(faculty);
		bag.insert(faculty);
		output.close();
		return faculty;
	}

	public static model.Title emitTitle() {
		return model.Title.values()[random.nextInt(model.Title.values().length)];
	}

	public static double emitSalary() {
		double salary = random.nextDouble() + random.nextInt(95000) + 35000;
		return salary;
	}

	public static model.Department emitDepartment() {
		return model.Department.values()[random.nextInt(model.Department.values().length)];
	}

	public static model.FacultyCourseStatus emitFacultyCourseStatus() {
		return model.FacultyCourseStatus.values()[random.nextInt(model.FacultyCourseStatus.values().length)];
	}

}
