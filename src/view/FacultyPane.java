package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Address;
import model.College;
import model.Department;
import model.Faculty;
import model.MiniFacultyCourseBag;
import model.Person;
import model.States;
import model.Title;
import model.ValueException;

public class FacultyPane {
	private GridPane facultyGrid;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField idField;
	private TextField salaryField;
	private TextField phoneField;
	private TextField streetAddressField;
	private TextField cityField;
	private TextField zipField;
	private Button insertBtn;
	private Button removeBtn;
	private Button findBtn;
	private Button updateBtn;
	final ComboBox<Department> deptDropDown = new ComboBox<Department>();
	final ComboBox<Title> titleDropDown = new ComboBox<Title>();
	final ComboBox<States> statesDropDown = new ComboBox<States>();
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
	private Label titleLbl;
	private Label deptLbl;
	private Label phoneLbl;
	private Label headerLbl;
	private Label salaryLbl;

	public FacultyPane(College college) {
		this.college = college;
		facultyGrid = new GridPane();
		back = new BackgroundFill(Color.rgb(221, 221, 221, 0.65), CornerRadii.EMPTY, Insets.EMPTY);
		backGr = new Background(back);
		facultyGrid.setMaxSize(1000, 500);
		facultyGrid.setBackground(backGr);
		facultyGrid.setGridLinesVisible(false);
		makeBtns();
		makeTextFields();
		setUpBtnListeners();
		makeLabels();
		populateGrid();
		facultyGrid.setVgap(20);
		facultyGrid.setHgap(20);
		facultyGrid.setPadding(new Insets(30));
	}

	private void populateGrid() {
		facultyGrid.add(firstNameField, 2, 5);
		facultyGrid.add(lastNameField, 4, 5);
		facultyGrid.add(phoneField, 4, 7);
		facultyGrid.add(idField, 2, 3);
		facultyGrid.add(findBtn, 2, 9);
		facultyGrid.add(insertBtn, 6, 9);
		facultyGrid.add(updateBtn, 4, 9);
		facultyGrid.add(statesDropDown, 6, 6);
		facultyGrid.add(streetAddressField, 2, 6);
		facultyGrid.add(cityField, 4, 6);
		facultyGrid.add(zipField, 2, 7);
		facultyGrid.add(headerLbl, 4, 1);
		facultyGrid.add(idLbl, 1, 3);
		facultyGrid.add(firstLbl, 1, 5);
		facultyGrid.add(lastLbl, 3, 5);
		facultyGrid.add(saLbl, 1, 6);
		facultyGrid.add(cityLbl, 3, 6);
		facultyGrid.add(stateLbl, 5, 6);
		facultyGrid.add(zipLbl, 1, 7);
		facultyGrid.add(phoneLbl, 3, 7);
		facultyGrid.add(titleLbl, 3, 3);
		facultyGrid.add(deptLbl, 5, 3);
		facultyGrid.add(salaryLbl, 5, 5);
		facultyGrid.add(salaryField, 6, 5);
		facultyGrid.add(titleDropDown, 4, 3);
		facultyGrid.add(deptDropDown, 6, 3);
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

	private void makeTextFields() {
		deptDropDown.getItems().addAll(Department.values());
		titleDropDown.getItems().addAll(Title.values());
		statesDropDown.getItems().addAll(States.values());
		firstNameField = new TextField();
		lastNameField = new TextField();
		idField = new TextField();
		salaryField = new TextField();
		phoneField = new TextField();
		streetAddressField = new TextField();
		cityField = new TextField();
		zipField = new TextField();
		firstNameField.setMaxSize(200, 40);
		firstNameField.setPromptText("First Name");
		lastNameField.setMinSize(200, 40);
		lastNameField.setPromptText("Last Name");
		idField.setMaxSize(200, 40);
		idField.setPromptText("ID");
		salaryField.setMinSize(150, 40);
		salaryField.setPromptText("Salary");
		phoneField.setMaxSize(200, 40);
		deptDropDown.setMinSize(200, 40);
		statesDropDown.setMinSize(200, 40);
		titleDropDown.setMinSize(200, 40);
		phoneField.setPromptText("Phone");
		streetAddressField.setPromptText("StreetName");
		streetAddressField.setMinSize(200, 40);
		cityField.setPromptText("City");
		cityField.setMaxSize(200, 40);
		zipField.setPromptText("Zip");
		zipField.setMaxSize(200, 40);
	}

	private void makeLabels() {
		headerLbl = new Label("Faculty View");
		headerLbl.setMinWidth(100);
		idLbl = new Label("ID Number:");
		idLbl.setMinWidth(50);
		firstLbl = new Label("First Name:");
		lastLbl = new Label("Last name:");
		saLbl = new Label("Street Address:");
		lastLbl.setMinWidth(100);
		saLbl.setMinWidth(140);
		cityLbl = new Label("City:");
		cityLbl.setMinWidth(60);
		stateLbl = new Label("State:");
		stateLbl.setMinWidth(120);
		zipLbl = new Label("Zip:");
		phoneLbl = new Label("Phone:");
		headerLbl.setAlignment(Pos.CENTER);
		salaryLbl = new Label("Salary:");
		deptLbl = new Label("Department:");
		titleLbl = new Label("Title:");
	}

	private void setUpBtnListeners() {
		insertBtn.setOnAction((e) -> {
			insert();
		});

		removeBtn.setOnAction((e) -> {
			delete();
		});

		findBtn.setOnAction((e) -> {
			search();
		});

		updateBtn.setOnAction((e) -> {
			update();
		});
	}
	
	public void insert(){
		if (college.getPersonBag().findById(idField.getText()) == null && idField.getText().equals("")) {
			try {
				MiniFacultyCourseBag miniFacultyCourseBag = new MiniFacultyCourseBag(
						college.getCourseBag().getnElems());
				Address address = new Address(streetAddressField.getText(), cityField.getText(),
						((States) statesDropDown.getValue()), zipField.getText());
				Faculty faculty = new Faculty(firstNameField.getText(), lastNameField.getText(), phoneField.getText(),
						address, ((Title) titleDropDown.getValue()),
						Double.parseDouble(salaryField.getText().replaceAll("[$,]", "")),
						((Department) deptDropDown.getValue()), miniFacultyCourseBag);
				college.getPersonBag().insert(faculty);
				idField.setText(faculty.getId());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New Listing");
				alert.setHeaderText("New Faculty addition.");
				alert.setContentText("Success.");
				alert.showAndWait();
			} catch (NumberFormatException | ValueException e1) {
				e1.printStackTrace();
			}
		}else if(idField.getText()!="" && college.getPersonBag().findById(idField.getText()) == null) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid ID error ");
			alert.setHeaderText("Unable to add record with this ID.");
			alert.setContentText("Clear the ID field to create a new record.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ID duplicate error ");
			alert.setHeaderText("ID already exists");
			alert.setContentText("Clear the ID field to create a new record.");
			alert.showAndWait();
		}
	}

	public void delete() {
		Person person = college.getPersonBag().findById(idField.getText());
		if (person != null) {
			if (person instanceof Faculty) {
				college.getPersonBag().deleteById(idField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Remove Listing");
				alert.setHeaderText("Faculty deletion.");
				alert.setContentText("Success.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Invalid Faculty ID");
				alert.setContentText("ID is not a Faculty ID.");
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

	public void update() {
		Person person = college.getPersonBag().findById(idField.getText());
		if (person != null) {
			if (person instanceof Faculty) {
				try {
					Faculty f = ((Faculty) person);
					f.setFirstName(firstNameField.getText());
					f.setLastName(lastNameField.getText());
					f.setDepartment((Department) deptDropDown.getValue());
					f.setTitle((Title) titleDropDown.getValue());
					person.getAddress().setStreetAddress(streetAddressField.getText());
					person.getAddress().setCity(cityField.getText());
					person.getAddress().setState((States) statesDropDown.getValue());
					person.setPhone(phoneField.getText());
					person.getAddress().setZip(zipField.getText());
					f.setSalary(Double.parseDouble(salaryField.getText().replaceAll("[$,]", "")));
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Listing Update");
					alert.setHeaderText("Faculty listing update.");
					alert.setContentText("Update complete.");
					alert.showAndWait();
				} catch (ValueException e2) {
					e2.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Invalid Faculty ID");
				alert.setContentText("ID is not a Faculty ID.");
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

	public void search() {
		Person person = college.getPersonBag().findById(idField.getText());
		if (person != null) {
			if (person instanceof Faculty) {
				Faculty f = ((Faculty) person);
				firstNameField.setText(f.getFirstName());
				lastNameField.setText(f.getLastName());
				titleDropDown.setValue(f.getTitle());
				phoneField.setText(f.getPhone());
				salaryField.setText(f.getSalary());
				deptDropDown.setValue(f.getDepartment());
				zipField.setText(person.getAddress().getZip());
				cityField.setText(person.getAddress().getCity());
				statesDropDown.setValue(person.getAddress().getState());
				streetAddressField.setText(person.getAddress().getStreetAddress());
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Invalid Faculty ID");
				alert.setContentText("ID is not a Faculty ID.");
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

	public TextField getIdField() {
		return idField;
	}

	public GridPane getGrid() {
		return facultyGrid;
	}
}
/*
 * Faculty: When clicked, a set of nested HBox and VBox will show up in the
 * central region of the BorderPane. These boxes contain TextFields that be used
 * to obtain input such as first name, last name, ID, salary, phone, etc., from
 * the user for a faculty member. These same TextFields will also be used to
 * display such information from the Faculty object to be found by ID number
 * from the person bag in the future. In addition to these TextFields, the set
 * of nested HBox and VBox contains four buttons: Insert, Remove, Find, and
 * Update. Insert will allow the user to insert a Faculty object using the
 * information collected from the TextFields into the person Bag. Remove will
 * somehow (your choice) obtain an ID number from the user and remove the
 * corresponding faculty object from the bag. Similarly, Find will find the
 * faculty object and display the information of that object by using an ID
 * number from the user, and Update will allow the user to make changes to the
 * Faculty object found and then save it back to the bag without deleting it.
 *
 */
