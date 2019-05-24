package model;

public class Student extends Person {
	/**
	 *
	 */
	private static final long serialVersionUID = 4135129651576721237L;
	private Major major;
	private double gpa;
	private MiniStudentCourseBag miniStudentCourseBag;
	public Student(String firstName, String lastName, Major major, MiniStudentCourseBag miniStudentCourseBag,String phone,Address address) throws ValueException{
		super(firstName, lastName,phone,address);
		this.major = major;
		this.miniStudentCourseBag = miniStudentCourseBag;
		this.gpa = calculateGPA(miniStudentCourseBag);
	}


	public void setGpa() {
		this.gpa = calculateGPA(miniStudentCourseBag);
	}


	public double calculateGPA(MiniStudentCourseBag miniStudentCourseBag) {
		double totalCredits = 0;

		LetterGrade grade = LetterGrade.F;
		double[] courseGrades = new double[miniStudentCourseBag.getnElems()];
		double gpa=0.0;
		double gradesTotal=0.0;
		for (int i = 0; i < miniStudentCourseBag.getnElems(); i++) {
			double credits =0;
			credits=miniStudentCourseBag.getMiniStudentInfoArray()[i].getNumberOfCredits();
			totalCredits += credits;
			grade = miniStudentCourseBag.getMiniStudentInfoArray()[i].getLetterGrade();

			switch (grade) {
			case A: {
				 courseGrades[i] = 4.0 * credits;
				break;
			}
			case B_PLUS: {
				courseGrades[i]  = 3.5 * credits;
				break;
			}
			case B: {
				courseGrades[i] = 3.0 * credits;
				break;
			}
			case C_PLUS: {
				courseGrades[i]  = 2.5 * credits;
				break;
			}
			case C: {
				courseGrades[i]  = 2.0 * credits;
				break;
			}
			case D_PLUS: {
				courseGrades[i]  = 1.5 * credits;
				break;
			}
			case D: {
				courseGrades[i]  = 1.0 * credits;
				break;
			}
			case F: {
				courseGrades[i]  = 0.0 * credits;
				break;
			}
			case PENDING:{
					totalCredits -=credits;
				break;
			}
			}
		}

		for(int i=0; i<courseGrades.length;i++ ) {
			gradesTotal += courseGrades[i];
		}
		gpa= (gradesTotal/(totalCredits));

		gpa=(((int)((gpa)*100))/100d);
		return gpa;
	}



	public Major getMajor() {
		return major;
	}

	public double getGpa() {
		return gpa;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBag;
	}

	public MiniStudentInfo[] getCoursesTaking() {
		return miniStudentCourseBag.findCoursesTaking();
	}

	public MiniStudentInfo[] getCoursesTaken() {
		return miniStudentCourseBag.findCoursesTaken();
	}

	public MiniStudentInfo[] getCoursesToTake() {
		return miniStudentCourseBag.findCoursesToTake();
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [major=" + major + ", gpa=" + gpa + ", miniStudentCourseBag=" + miniStudentCourseBag
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getId()=" + getId()
				+ ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress() + "]";
	}







}
