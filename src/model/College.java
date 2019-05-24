package model;

public class College {
	private PersonBag personBag;
	private TextbookBag textbookBag;
	private ClassroomBag classroomBag;
	private CourseBag courseBag;
	private MiniFacultyCourseBag miniFacultyCourseBag;
	private MiniStudentCourseBag miniStudentCourseBag;

	public College(int maxPersonBag, int maxTextbookBag, int maxClassroomBag, int maxCourseBag,
			int maxMiniFacultyCourseBag, int maxMiniStudentCourseBag) {
		personBag = new PersonBag(maxPersonBag);
		textbookBag = new TextbookBag(maxTextbookBag);
		courseBag = new CourseBag(maxCourseBag);
		classroomBag = new ClassroomBag(maxClassroomBag);
		miniFacultyCourseBag = new MiniFacultyCourseBag(maxMiniFacultyCourseBag);
		miniStudentCourseBag = new MiniStudentCourseBag(maxMiniStudentCourseBag);

	}

	public PersonBag getPersonBag() {
		return personBag;
	}

	public void setPersonBag(PersonBag personBag) {
		this.personBag = personBag;
	}

	public TextbookBag getTextbookBag() {
		return textbookBag;
	}

	public void setTextbookBag(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
	}

	public ClassroomBag getClassroomBag() {
		return classroomBag;
	}

	public void setClassroomBag(ClassroomBag classroomBag) {
		this.classroomBag = classroomBag;
	}

	public CourseBag getCourseBag() {
		return courseBag;
	}

	public void setCourseBag(CourseBag courseBag) {
		this.courseBag = courseBag;
	}

	public MiniFacultyCourseBag getMiniFacultyCourseBag() {
		return miniFacultyCourseBag;
	}

	public void setMiniFacultyCourseBag(MiniFacultyCourseBag miniFacultyCourseBag) {
		this.miniFacultyCourseBag = miniFacultyCourseBag;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBag;
	}

	public void setMiniStudentCourseBag(MiniStudentCourseBag miniStudentCourseBag) {
		this.miniStudentCourseBag = miniStudentCourseBag;
	}


}