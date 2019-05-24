package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.College;
import utils.Utils;

public class BottomPane {
	private Button studentBtn;
	private Button facultyBtn;
	private Button textbookBtn;
	private Button classroomBtn;
	private Button courseBtn;
	private Button backupBtn;
	private Button restoreBtn;
	private Button exitBtn;
	private BorderPane root;
	private HBox buttonBox;
	private College college;
	private StudentPane studentView;

	public BottomPane(College college, BorderPane root) {
		this.college = college;
		this.root = root;
		makeBtns();
		setUpBtnListeners();
		buttonBox = new HBox(30);
		buttonBox.getChildren().addAll(studentBtn, facultyBtn, textbookBtn, classroomBtn,courseBtn);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.setPadding(new Insets(30));
	}

	private void makeBtns() {
		studentBtn = new Button("Student");
		studentBtn.setPrefSize(120, 50);
		facultyBtn = new Button("Faculty");
		facultyBtn.setPrefSize(120, 50);
		textbookBtn = new Button("Textbook");
		textbookBtn.setPrefSize(120, 50);
		classroomBtn = new Button("Classroom");
		classroomBtn.setPrefSize(120, 50);
		courseBtn = new Button("Courses");
		courseBtn.setPrefSize(120, 50);
		backupBtn = new Button("Backup");
		backupBtn.setPrefSize(120, 50);
		restoreBtn = new Button("Restore");
		restoreBtn.setPrefSize(120, 50);
		exitBtn = new Button("Exit");
		exitBtn.setPrefSize(120, 50);
	}

	private void setUpBtnListeners() {
		studentBtn.setOnAction((e) -> {
		StudentPane studentView = new StudentPane(college);
		root.setCenter(studentView.getGrid());
		});

		facultyBtn.setOnAction((e) -> {
			FacultyPane facultyView = new FacultyPane(college);
			root.setCenter(facultyView.getGrid());
			});

		textbookBtn.setOnAction((e) -> {
			TextbookPane textbookView = new TextbookPane(college);
			root.setCenter(textbookView.getGrid());
			});

		classroomBtn.setOnAction((e) -> {
			ClassroomPane classroomView = new ClassroomPane(college);
			root.setCenter(classroomView.getGrid());
			});

		courseBtn.setOnAction((e) -> {
			CoursePane courseView = new CoursePane(college);
			root.setCenter(courseView.getCourseBox());
			});

		restoreBtn.setOnAction((e) -> {
			Utils.restore(college);
			});

		backupBtn.setOnAction((e) -> {
			Utils.backup(college.getClassroomBag(),college.getCourseBag(),college.getTextbookBag(),college.getPersonBag(), college);
			});

		exitBtn.setOnAction(e -> {
			Utils.backup(college.getClassroomBag(),college.getCourseBag(),college.getTextbookBag(),college.getPersonBag(), college);
			Platform.exit();
		});

	}

	public Button getStudentBtn() {
		return studentBtn;
	}

	public Button getFacultyBtn() {
		return facultyBtn;
	}

	public Button getTextbookBtn() {
		return textbookBtn;
	}

	public Button getClassroomBtn() {
		return classroomBtn;
	}

	public Button getBackupBtn() {
		return backupBtn;
	}

	public Button getRestoreBtn() {
		return restoreBtn;
	}

	public Button getExitBtn() {
		return exitBtn;
	}

	public Node getButtonBox() {
		return buttonBox;
	}

	public Button getCourseBtn() {
		return courseBtn;
	}

	public StudentPane getStudentView() {

		return studentView;
	}

}
