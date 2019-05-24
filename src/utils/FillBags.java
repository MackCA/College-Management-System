//package utils;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import app.App;
//import model.College;
//import model.Load;
//
//public class FillBags {
//	static College college;
//	
//	public static void fillBags() {
//		StudentFactory sf = new utils.StudentFactory(college.getPersonBag());
//		FacultyFactory ff = new utils.FacultyFactory(college.getPersonBag());
//		utils.CourseFactory cf = new utils.CourseFactory(college.getCourseBag(), "Course_Inventory.txt");
//		utils.TextbookFactory tf = new utils.TextbookFactory(college.getTextbookBag());
//		utils.ClassroomFactory crf = new utils.ClassroomFactory(college.getClassroomBag());
//		utils.MiniFacultyCourseFactory mfb = new utils.MiniFacultyCourseFactory(college.getMiniFacultyCourseBag(), college.getCourseBag());
//		utils.MiniStudentCourseFactory msb = new utils.MiniStudentCourseFactory(college.getMiniStudentCourseBag(), college.getCourseBag());
//		try {
//			utils.NameFactory.generateNameArrays();
//			cf.importCourse();
//			tf.importTextbook();
//			for (int i = 0; i < 500; i++) {
//				ff.generateFaculty();
//			}
//			for (int i = 0; i < 2000; i++) {
//				sf.generateStudent();
//			}
//			for (int i = 0; i < 50; i++) {
//				crf.generateClassroom();
//			}
//		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//		} catch (IOException exc) {
//			exc.printStackTrace();
//		}
//	}
//}