package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class CourseFactory {
	private static model.CourseBag courseBag;
	private static String courseFileName;
	static Random random = new Random();
	static String courseNumber;
	static String courseTitle;
	static String courseDescription;
	static String creditValue;
	private static int courseListLength = 1000;

	public CourseFactory(model.CourseBag courseBag,String courseFileName) {
		super();
		CourseFactory.courseBag = courseBag;
		CourseFactory.courseFileName = courseFileName;
	}

	public static model.Course importCourse() throws FileNotFoundException {
		Scanner input;
		File file = new File("data\\import\\"+courseFileName);
		input = new Scanner(file);
		PrintWriter output = new PrintWriter("data\\export\\Cleaned_Course_List.txt");
		model.Course course = null;
			while (input.hasNextLine()) {
				for(int i=0; i<courseListLength; i++) {
				do {
					try {
						courseNumber = input.nextLine();
						courseTitle = input.nextLine();
						courseDescription = input.nextLine();
						creditValue = input.nextLine();
						input.nextLine();
					} catch (Exception e) {
						break;
					}
				} while (courseTitle.equals(""));
				course= new model.Course(courseNumber,courseTitle,courseDescription,Double.parseDouble(creditValue));
				output.println(course);
				courseBag.insert(course);

			}
				break;
		}
		input.close();
		output.close();
		return course;
	}

	public static model.CourseBag getCourseBag() {
		return courseBag;
	}

}
