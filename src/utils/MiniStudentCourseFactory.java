package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import model.CourseBag;
import model.CourseStatus;
import model.MiniStudentCourseBag;
import model.MiniStudentInfo;

public class MiniStudentCourseFactory {
	static Random random = new Random();
	static model.MiniStudentCourseBag bag;
	private static CourseBag courseBag;
	private static int sCourseBagMax = 50;
	private static int maxCourseList = 20;

	public MiniStudentCourseFactory(MiniStudentCourseBag miniStudentCourseBag, model.CourseBag courseBag) {
		this.bag = miniStudentCourseBag;
		this.courseBag = courseBag;
	}

	public static model.MiniStudentInfo emitStudentInfo() {
		model.Course course = courseBag.getCourseArray()[random.nextInt(courseBag.getnElems())];

		model.MiniStudentInfo miniStudentInfo = new model.MiniStudentInfo(course.getCourseNumber(), course.getCourseTitle(),course.getCourseDescription(),
				model.LetterGrade.emitLetterGrades(), course.getNumberOfCredits(), emitCourseStatus());

		if(miniStudentInfo.getCourseStatus()!=CourseStatus.TAKEN) {
			miniStudentInfo.setLetterGrade(model.LetterGrade.PENDING);
		}
		else if(miniStudentInfo.getCourseStatus()==CourseStatus.TAKEN){
			while(miniStudentInfo.getLetterGrade()==model.LetterGrade.PENDING) {
				miniStudentInfo.setLetterGrade(model.LetterGrade.emitLetterGrades());
			}
		}
		return miniStudentInfo;
	}

	public static model.MiniStudentCourseBag emitMiniStudentCourseBag() throws IOException {
		FileWriter fw = new FileWriter("data\\export\\Student Course List.txt", true);
		PrintWriter output = new PrintWriter(fw);

		MiniStudentInfo[] courses = new MiniStudentInfo[courseBag.getnElems()];//choose from all courses
		int maxClassListLength = random.nextInt(maxCourseList) + 1;//cap # of classes put into bag
		MiniStudentCourseBag bag = new MiniStudentCourseBag(sCourseBagMax);
		
		for (int i = 0; i < courseBag.getnElems(); i++) {
			courses[i] = emitStudentInfo();
			output.println(courses[i]);
		}
		for (int i = 0; i < maxClassListLength; i++) {
			bag.insert(courses[random.nextInt(courses.length)]);
		}
		output.close();
		return bag;
	}

	public static model.CourseStatus emitCourseStatus() {
		return model.CourseStatus.values()[random.nextInt(model.CourseStatus.values().length)];
	}

}
