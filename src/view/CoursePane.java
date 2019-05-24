package view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.College;
import model.Course;
import model.CourseBag;
import model.CourseStatus;
import model.Faculty;
import model.FacultyCourseStatus;
import model.LetterGrade;
import model.MiniFacultyCourseInfo;
import model.MiniStudentInfo;
import model.Person;
import model.Student;

public class CoursePane {
	private VBox courseBox;
	private HBox listA;
	private TextField idField;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField gpaField;
	private College college;
	private Button searchBtn;
	private Button setAToBBtn;
	private Button setBToABtn;
	private Button setCToBBtn;
	private Button setBToCBtn;
	private Button addCoursesBtn;
	private Button addBtn;
	private Button removeBtn;
	private ListView<MiniStudentInfo> lvCoursesTaken;
	private ListView<MiniStudentInfo> lvCoursesTaking;
	private ListView<MiniStudentInfo> lvCoursesToTake;
	private ListView<MiniFacultyCourseInfo> lvCoursesTaught;
	private ListView<MiniFacultyCourseInfo> lvCoursesTeaching;
	private ListView<Course> lvCourseList;
	private VBox btnBoxAB;
	private VBox btnBoxBC;
	private Person person;
	private MultipleSelectionModel<MiniStudentInfo> listModelSA;
	private MultipleSelectionModel<MiniStudentInfo> listModelSB;
	private MultipleSelectionModel<MiniStudentInfo> listModelSC;
	private MultipleSelectionModel<MiniFacultyCourseInfo> listModelFA;
	private MultipleSelectionModel<MiniFacultyCourseInfo> listModelFB;
	private MultipleSelectionModel<Course> listModelCL;
	private BackgroundFill back;
	private Background backGr;
	private Label header;

	public CoursePane(College college) {
		this.college = college;
		courseBox = new VBox(20);
		back = new BackgroundFill(Color.rgb(221, 221, 221, 0.65), CornerRadii.EMPTY, Insets.EMPTY);
		backGr = new Background(back);
		courseBox.setMaxSize(1250, 650);
		courseBox.setMinSize(1250, 650);
		courseBox.setBackground(backGr);
		listA = new HBox(50);
		listA.setAlignment(Pos.BOTTOM_CENTER);
		listA.setMinSize(500, 500);
		makeTextFields();
		makeBtns();
		setUpBtnListeners();
		header = new Label();
		Label prompt = new Label("Enter a ID Number:");
		HBox titleBox = new HBox();
		HBox lblBox = new HBox(30);
		titleBox.getChildren().add(header);
		lblBox.getChildren().addAll(prompt, idField, firstNameField, lastNameField, searchBtn, addCoursesBtn,removeBtn);
		lblBox.setAlignment(Pos.TOP_CENTER);
		courseBox.getChildren().addAll(titleBox, lblBox, listA);
		courseBox.setAlignment(Pos.CENTER);
		header.setTextAlignment(TextAlignment.CENTER);
	}

	private void setUpBtnListeners() {
		searchBtn.setOnAction((e) -> {
			person = college.getPersonBag().findById(idField.getText());
			if (person != null) {
				if (person instanceof Student) {
					Student s = ((Student) person);
					firstNameField.setText(s.getFirstName());
					lastNameField.setText(s.getLastName());
					lvStudent();
				} else {
					Faculty f = ((Faculty) person);
					firstNameField.setText(f.getFirstName());
					lastNameField.setText(f.getLastName());
					lvFaculty();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Not found.");
				alert.setContentText("ID not found.");
				alert.showAndWait();
			}
		});

		// Col A = Taken/Taught Col B = Taking/Teaching Col C = ToTake
		setAToBBtn.setOnAction((e) -> {
			if (person instanceof Student) {
				listModelSA.getSelectedItem().setCourseStatus(CourseStatus.TAKING);
				((Student) person).setGpa();
				lvStudent();
			} else {
				listModelFA.getSelectedItem().setFacultyCourseStatus(FacultyCourseStatus.TEACHING);
				lvFaculty();
			}
		});
		setBToABtn.setOnAction((e) -> {
			if (person instanceof Student) {
				listModelSB.getSelectedItem().setCourseStatus(CourseStatus.TAKEN);
				ChoiceDialog<LetterGrade> dialog = new ChoiceDialog<LetterGrade>(LetterGrade.A, LetterGrade.B_PLUS,
						LetterGrade.B, LetterGrade.C_PLUS, LetterGrade.C, LetterGrade.D_PLUS, LetterGrade.D,
						LetterGrade.F, LetterGrade.PENDING);
				dialog.setTitle("Grade");
				dialog.setHeaderText("Enter the course letter grade:");
				dialog.setContentText("Grade:");
				Optional<LetterGrade> result = dialog.showAndWait();
				result.ifPresent(grades -> {
					listModelSB.getSelectedItem().setLetterGrade(result.get());
					((Student) person).setGpa();
				});
				lvStudent();
			} else {
				listModelFB.getSelectedItem().setFacultyCourseStatus(FacultyCourseStatus.TAUGHT);
				lvFaculty();
			}
		});

		setCToBBtn.setOnAction((e) -> {
			if (person instanceof Student) {
				listModelSC.getSelectedItem().setCourseStatus(CourseStatus.TAKING);
				lvStudent();
			}
		});
		setBToCBtn.setOnAction((e) -> {
			listModelSB.getSelectedItem().setCourseStatus(CourseStatus.TOTAKE);
			lvStudent();
		});

		addBtn.setOnAction((e) -> {
			if (person instanceof Student) {
				Student s = (Student) person;
				MiniStudentInfo newCourse = new MiniStudentInfo(listModelCL.getSelectedItem().getCourseNumber(),listModelCL.getSelectedItem().getCourseTitle(),
						listModelCL.getSelectedItem().getCourseDescription(),LetterGrade.PENDING,listModelCL.getSelectedItem().getNumberOfCredits(),CourseStatus.TOTAKE);
				s.getMiniStudentCourseBag().insert(newCourse);
				lvStudent();
			} else {
				Faculty f = (Faculty) person;
				MiniFacultyCourseInfo newCourse = new MiniFacultyCourseInfo(listModelCL.getSelectedItem().getCourseNumber(),listModelCL.getSelectedItem().getCourseTitle(),
						listModelCL.getSelectedItem().getCourseDescription(),listModelCL.getSelectedItem().getNumberOfCredits(),FacultyCourseStatus.TEACHING);
				f.getMiniFacultyCourseBag().insert(newCourse);
				lvFaculty();
			}
		});

		addCoursesBtn.setOnAction((e) -> {
			header.setText("");
			listA.getChildren().clear();
			ObservableList<Course> courseList = FXCollections.observableArrayList(college.getCourseBag().getCourseArray());
			lvCourseList = new ListView<>(courseList);
			lvCourseList.setPrefSize(300, 150);
			listModelCL = lvCourseList.getSelectionModel();
			listModelCL.setSelectionMode(SelectionMode.MULTIPLE);
			listA.getChildren().addAll(lvCourseList, addBtn);
			header.setText("Full Course List");
		});

		removeBtn.setOnAction((e) -> {
			if (person instanceof Student) {
				Student s = (Student) person;
				if(listModelSA.getSelectedItem() != null){
				s.getMiniStudentCourseBag().deleteByCourseNumber(listModelSA.getSelectedItem().getCourseNumber());
				}else if(listModelSB.getSelectedItem()!=null){
				s.getMiniStudentCourseBag().deleteByCourseNumber(listModelSB.getSelectedItem().getCourseNumber());
				}else if(listModelSC.getSelectedItem() != null){
				s.getMiniStudentCourseBag().deleteByCourseNumber(listModelSC.getSelectedItem().getCourseNumber());
				}else{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText("Invalid Selection.");
					alert.setContentText("Please select the course to remove.");
					alert.showAndWait();
				}
				lvStudent();
			} else {
				Faculty f = (Faculty) person;
				if(listModelFA.getSelectedItem() != null){
				f.getMiniFacultyCourseBag().deleteByCourseNumber(listModelFA.getSelectedItem().getCourseNumber());
				}else if(listModelFB.getSelectedItem() != null){
				f.getMiniFacultyCourseBag().deleteByCourseNumber(listModelFB.getSelectedItem().getCourseNumber());
				}else{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText("Invalid Selection.");
					alert.setContentText("Please select the course to remove.");
					alert.showAndWait();
				}
				lvFaculty();
			}
		});
	}

	private void makeTextFields() {
		idField = new TextField();
		idField.setMaxSize(200, 25);
		idField.setPromptText("ID");
		firstNameField = new TextField();
		firstNameField.setMaxSize(200, 25);
		firstNameField.setPromptText("First Name");
		lastNameField = new TextField();
		lastNameField.setMaxSize(200, 25);
		lastNameField.setPromptText("Last Name");
		gpaField = new TextField();
		gpaField.setEditable(false);
		gpaField.setPromptText("GPA");
		gpaField.setMaxSize(200, 25);
	}

	private void makeBtns() {
		searchBtn = new Button("Search");
		searchBtn.setPrefSize(100, 50);
		addCoursesBtn = new Button("Courses");
		addCoursesBtn.setPrefSize(100, 50);
		addBtn = new Button("Add");
		addBtn.setPrefSize(100, 50);
		removeBtn = new Button("Remove");
		removeBtn.setPrefSize(100, 50);
		setAToBBtn = new Button("--->");
		setAToBBtn.setPrefSize(90, 50);
		setBToABtn = new Button("<---");
		setBToABtn.setPrefSize(90, 50);
		setCToBBtn = new Button("<---");
		setCToBBtn.setPrefSize(90, 50);
		setBToCBtn = new Button("--->");
		setBToCBtn.setPrefSize(90, 50);
		btnBoxAB = new VBox(80);
		btnBoxAB.setAlignment(Pos.CENTER);
		btnBoxBC = new VBox(80);
		btnBoxBC.setAlignment(Pos.CENTER);
		btnBoxAB.getChildren().addAll(setAToBBtn, setBToABtn);
		btnBoxBC.getChildren().addAll(setBToCBtn, setCToBBtn);
	}

	public void lvStudent() {
		header.setText("");
		listA.getChildren().clear();
		Student s = (Student) person;
		ObservableList<MiniStudentInfo> coursesTaken = FXCollections
				.observableArrayList(s.getMiniStudentCourseBag().findCoursesTaken());
		ObservableList<MiniStudentInfo> coursesTaking = FXCollections
				.observableArrayList(s.getMiniStudentCourseBag().findCoursesTaking());
		ObservableList<MiniStudentInfo> coursesToTake = FXCollections
				.observableArrayList(s.getMiniStudentCourseBag().findCoursesToTake());
		lvCoursesTaken = new ListView<>(coursesTaken);
		lvCoursesTaking = new ListView<>(coursesTaking);
		lvCoursesToTake = new ListView<>(coursesToTake);
		lvCoursesTaken.setPrefSize(300, 150);
		lvCoursesTaking.setPrefSize(300, 150);
		lvCoursesToTake.setPrefSize(300, 150);
		listModelSA = lvCoursesTaken.getSelectionModel();
		listModelSB = lvCoursesTaking.getSelectionModel();
		listModelSC = lvCoursesToTake.getSelectionModel();
		listModelSA.setSelectionMode(SelectionMode.SINGLE);
		listModelSB.setSelectionMode(SelectionMode.SINGLE);
		listModelSC.setSelectionMode(SelectionMode.SINGLE);
		listA.getChildren().addAll(lvCoursesTaken, btnBoxAB, lvCoursesTaking, btnBoxBC, lvCoursesToTake);
		header.setText("Student Course Record");
	}

	public void lvFaculty() {
		header.setText("");
		listA.getChildren().clear();
		Faculty f = (Faculty) person;
		ObservableList<MiniFacultyCourseInfo> coursesTaught = FXCollections
				.observableArrayList(f.getMiniFacultyCourseBag().findCoursesTaught());
		ObservableList<MiniFacultyCourseInfo> coursesTeaching = FXCollections
				.observableArrayList(f.getMiniFacultyCourseBag().findCoursesTeaching());
		lvCoursesTaught = new ListView<>(coursesTaught);
		lvCoursesTeaching = new ListView<>(coursesTeaching);
		lvCoursesTaught.setPrefSize(300, 150);
		lvCoursesTeaching.setPrefSize(300, 150);
		listModelFA = lvCoursesTaught.getSelectionModel();
		listModelFB = lvCoursesTeaching.getSelectionModel();
		listModelFA.setSelectionMode(SelectionMode.MULTIPLE);
		listModelFB.setSelectionMode(SelectionMode.MULTIPLE);
		listA.getChildren().addAll(lvCoursesTaught, btnBoxAB, lvCoursesTeaching);
		header.setText("Faculty Course Record");
	}

	public VBox getCourseBox() {
		return courseBox;
	}

}
