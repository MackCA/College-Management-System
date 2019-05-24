 package utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import model.MiniFacultyCourseBag;

public class MiniFacultyCourseFactory {

	static Random random = new Random();
	static model.MiniFacultyCourseBag bag;
	static model.CourseBag courseBag;
	static int maxNumCourses=20;
	static int fCourseBagMax =50;
			

	public MiniFacultyCourseFactory(MiniFacultyCourseBag miniFacultyCourseBag, model.CourseBag courseBag) {
		this.bag = miniFacultyCourseBag;
		this.courseBag = courseBag;
	}

	public static model.MiniFacultyCourseInfo emitFacultyInfo() throws FileNotFoundException {
		model.Course course = courseBag.getCourseArray()[random.nextInt(courseBag.getnElems())];
		model.MiniFacultyCourseInfo miniFacultyInfo = new model.MiniFacultyCourseInfo(course.getCourseNumber(),course.getCourseTitle(),course.getCourseDescription(),course.getNumberOfCredits(),
				emitFacultyCourseStatus());
		return miniFacultyInfo;
	}

	public static model.MiniFacultyCourseBag emitMiniFacultyCourseBag() throws IOException {
		FileWriter fw = new FileWriter("data\\export\\Faculty Course List.txt", true);
		PrintWriter output = new PrintWriter(fw);

		model.MiniFacultyCourseInfo[] courses = new model.MiniFacultyCourseInfo[courseBag.getnElems()];//include all posible courses in search range
		int maxClassListLength = random.nextInt(maxNumCourses) + 1;//cap # of courses put into bag
		MiniFacultyCourseBag bag = new MiniFacultyCourseBag(fCourseBagMax);
		
		for (int i = 0; i < courseBag.getnElems(); i++) {
			courses[i] = emitFacultyInfo();
			output.println(courses[i]);
		}
		for (int i = 0; i < maxClassListLength; i++) {
			bag.insert(courses[random.nextInt(courses.length)]);
		}
		output.close();
		return bag;
	}

	public static model.FacultyCourseStatus emitFacultyCourseStatus() {
		return model.FacultyCourseStatus.values()[random.nextInt(model.FacultyCourseStatus.values().length)];
	}

}
