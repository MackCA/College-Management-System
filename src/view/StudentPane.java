package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Address;
import model.College;
import model.Major;
import model.MiniStudentCourseBag;
import model.Person;
import model.States;
import model.Student;
import model.ValueException;

public class StudentPane {
	private GridPane studentGrid;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField idField;
	private TextField gpaField;
	private TextField phoneField;
	private TextField streetAddressField;
	private TextField cityField;
	private TextField zipField;
	private Button insertBtn;
	private Button removeBtn;
	private Button findBtn;
	private Button updateBtn;
	final private ComboBox<Major> majorDropDown = new ComboBox<Major>();
	final private ComboBox<States> statesDropDown = new ComboBox<States>();
	private College college;
	private BackgroundFill back;
	private Background backGr;
	private Label idLbl;
	private Label firstLbl;
	private Label lastLbl;
	private Label saLbl;
	private Label cityLbl;
	private Label stateLbl;
	private Label zipLbl;
	private Label gpaLbl;
	private Label majorLbl;
	private Label phoneLbl;
	private Label headerLbl;

	public StudentPane(College college) {
		this.college = college;
		studentGrid = new GridPane();
		back = new BackgroundFill(Color.rgb(221, 221, 221, 0.65), CornerRadii.EMPTY, Insets.EMPTY);
		backGr = new Background(back);
		studentGrid.setMaxSize(1000, 500);
		studentGrid.setBackground(backGr);
		studentGrid.setGridLinesVisible(false);
		makeBtns();
		makeTextFields();
		setUpBtnListeners();
		makeLabels();
		populateGrid();
		studentGrid.setVgap(20);
		studentGrid.setHgap(20);
		studentGrid.setPadding(new Insets(40));
	}

	private void populateGrid() {
		studentGrid.add(firstNameField, 2, 5);
		studentGrid.add(lastNameField, 4, 5);
		studentGrid.add(gpaField, 6, 3);
		studentGrid.add(phoneField, 4, 7);
		studentGrid.add(idField, 2, 3);
		studentGrid.add(findBtn, 2, 9);
		studentGrid.add(insertBtn, 6, 9);
		studentGrid.add(updateBtn, 4, 9);
		studentGrid.add(majorDropDown, 4, 3);
		studentGrid.add(statesDropDown, 6, 6);
		studentGrid.add(streetAddressField, 2, 6);
		studentGrid.add(cityField, 4, 6);
		studentGrid.add(zipField, 2, 7);
		studentGrid.add(headerLbl, 3, 1);
		studentGrid.add(idLbl, 1, 3);
		studentGrid.add(firstLbl, 1, 5);
		studentGrid.add(lastLbl, 3, 5);
		studentGrid.add(saLbl, 1, 6);
		studentGrid.add(cityLbl, 3, 6);
		studentGrid.add(stateLbl, 5, 6);
		studentGrid.add(zipLbl, 1, 7);
		studentGrid.add(phoneLbl, 3, 7);
		studentGrid.add(majorLbl, 3, 3);
		studentGrid.add(gpaLbl, 5, 3);
	}

	private void makeBtns() {
		insertBtn = new Button("Insert");
		insertBtn.setPrefSize(90, 50);
		removeBtn = new Button("Remove");
		removeBtn.setPrefSize(90, 50);
		findBtn = new Button("Find");
		findBtn.setPrefSize(90, 50);
		updateBtn = new Button("Update");
		updateBtn.setPrefSize(90, 50);
	}

	private void makeLabels() {
		headerLbl = new Label("Student View");
		headerLbl.setMinWidth(140);
		idLbl = new Label("ID Number:");
		idLbl.setMinWidth(50);
		firstLbl = new Label("First Name:");
		lastLbl = new Label("Last name:");
		saLbl = new Label("Street Address:");
		saLbl.setMinWidth(140);
		cityLbl = new Label("City:");
		stateLbl = new Label("State:");
		stateLbl.setMinWidth(60);
		zipLbl = new Label("Zip:");
		phoneLbl = new Label("Phone:");
		headerLbl.setAlignment(Pos.CENTER);
		gpaLbl = new Label("GPA:");
		majorLbl = new Label("Major:");
	}

	private void makeTextFields() {
		majorDropDown.getItems().addAll(Major.values());
		majorDropDown.setMinSize(200, 40);
		statesDropDown.getItems().addAll(States.values());
		statesDropDown.setMinSize(200, 40);
		firstNameField = new TextField();
		firstNameField.setMinSize(200, 40);
		firstNameField.setPromptText("First Name");
		lastNameField = new TextField();
		lastNameField.setMinSize(200, 40);
		lastNameField.setPromptText("Last Name");
		idField = new TextField();
		idField.setMaxSize(200, 40);
		idField.setPromptText("ID");
		gpaField = new TextField();
		gpaField.setEditable(false);
		gpaField.setPromptText("GPA");
		gpaField.setMinSize(100, 40);
		streetAddressField = new TextField();
		streetAddressField.setPromptText("Street Address");
		streetAddressField.setMinSize(200, 40);
		cityField = new TextField();
		cityField.setPromptText("City");
		cityField.setMinSize(200, 40);
		zipField = new TextField();
		zipField.setPromptText("Zip");
		zipField.setMinSize(200, 40);
		phoneField = new TextField();
		phoneField.setPromptText("Phone");
		phoneField.setMinSize(200, 40);
	}

	private void setUpBtnListeners() {
		insertBtn.setOnAction((e) -> {
			if (college.getPersonBag().findById(idField.getText()) == null && idField.getText().equals("")) {
				try {
					Address address = new Address(streetAddressField.getText(), cityField.getText(),
							((States) statesDropDown.getValue()), zipField.getText());
					MiniStudentCourseBag miniStudentCourseBag = new MiniStudentCourseBag(
							college.getCourseBag().getnElems());
					Student student = new Student(firstNameField.getText(), lastNameField.getText(),
							((Major) majorDropDown.getValue()), miniStudentCourseBag, phoneField.getText(), address);
					college.getPersonBag().insert(student);
					idField.setText(student.getId());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("New Listing");
					alert.setHeaderText("New Student: ID number: "+student.getId());
					alert.setContentText("Success.");
					alert.showAndWait();
				} catch (ValueException e1) {
					e1.printStackTrace();
				}
			} else if(idField.getText()!=""&& college.getPersonBag().findById(idField.getText()) == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid ID error ");
				alert.setHeaderText("Unable to add record with this ID.");
				alert.setContentText("Clear the ID field to create a new record.");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ID duplicate error ");
				alert.setHeaderText("ID already exists");
				alert.setContentText("Clear the ID field to create a new record.");
				alert.showAndWait();
			}
		});

		removeBtn.setOnAction((e) -> {
			remove();
		});

		findBtn.setOnAction((e) -> {
			search();
		});

		updateBtn.setOnAction((e) -> {
		update();
		});
	}
	
	public void remove(){
		Person person = college.getPersonBag().findById(idField.getText());
		if (person != null) {
			if (person instanceof Student) {
				college.getPersonBag().deleteById(idField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Remove Listing");
				alert.setHeaderText("Student deletion.");
				alert.setContentText("Success.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Invalid Student ID");
				alert.setContentText("ID is not a Student ID.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("ID not found.");
			alert.showAndWait();
		}
	}
	public void search(){
		Person person = college.getPersonBag().findById(idField.getText());
		if (person != null) {
			if (person instanceof Student) {
				Student s = ((Student) person);
				firstNameField.setText(s.getFirstName());
				lastNameField.setText(s.getLastName());
				majorDropDown.setValue(s.getMajor());
				gpaField.setText("" + s.getGpa());
				statesDropDown.setValue(person.getAddress().getState());
				streetAddressField.setText(person.getAddress().getStreetAddress());
				phoneField.setText(person.getPhone());
				zipField.setText(person.getAddress().getZip());
				cityField.setText(person.getAddress().getCity());
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Invalid Student ID");
				alert.setContentText("ID is not a Student ID.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("ID not found.");
			alert.showAndWait();
		}
	}
	
	public void update(){
		Person person = college.getPersonBag().findById(idField.getText());
		if (person != null) {
			if (person instanceof Student) {
				Student s = ((Student) person);
				try {
					s.setFirstName(firstNameField.getText());
					s.setLastName(lastNameField.getText());
					person.getAddress().setStreetAddress(streetAddressField.getText());
					person.getAddress().setCity(cityField.getText());
					person.setPhone(phoneField.getText());
					s.setMajor((Major) majorDropDown.getValue());
					person.getAddress().setState((States) statesDropDown.getValue());
					person.getAddress().setZip(zipField.getText());
					gpaField.setText("" + s.getGpa());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Listing Update!");
					alert.setHeaderText("Student listing update.");
					alert.setContentText("Update complete.");
					alert.showAndWait();
				} catch (ValueException e1) {
					e1.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Invalid Student ID");
				alert.setContentText("ID is not a Student ID.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("ID not found.");
			alert.showAndWait();
		}
	}

	public GridPane getGrid() {
		return studentGrid;
	}

	public TextField getIdField() {
		return idField;
	}

	public void setIdField(TextField idField) {
		this.idField = idField;
	}

}
