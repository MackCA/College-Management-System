package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import model.Address;
import model.Major;
import model.Student;
import model.ValueException;

public class StudentFactory {
	private static model.Student student;
	private static Random random = new Random();
	private static model.PersonBag bag;


	public StudentFactory(model.PersonBag bag) {
		super();
		this.bag = bag;
	}

	public static Student getStudent() {
		return student;
	}

	public static model.Student generateStudent() throws IOException, ValueException{
		FileWriter fw = new FileWriter("data\\export\\Student List.txt",true);
		PrintWriter output = new PrintWriter(fw);
		model.Student student = null;
				String firstName= NameFactory.emitFirstName();
				String lastName= NameFactory.emitLastName();
				Major major=emitMajor();
				String phone = ContactInfoFactory.emitPhone();
				Address address = ContactInfoFactory.emitAddress();
				model.MiniStudentCourseBag miniStudentCourseBag =MiniStudentCourseFactory.emitMiniStudentCourseBag();
				student = new model.Student(firstName,lastName,major,miniStudentCourseBag,phone, address);
				output.println(student);
				bag.insert(student);
		output.close();
		return student;
	}

	public static model.Major emitMajor() {
		return model.Major.values()[random.nextInt(model.Major.values().length)];
	}


}
