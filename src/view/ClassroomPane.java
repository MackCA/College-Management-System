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
import javafx.scene.text.TextAlignment;
import model.Building;
import model.Classroom;
import model.College;
import model.ValueException;

public class ClassroomPane {
	private static GridPane classroomGrid;
	private TextField bldgNameField;
	private TextField roomNumberField;
	private TextField capacityField;
	private Button insertBtn;
	private Button removeBtn;
	private Button findBtn;
	private Button updateBtn;
	final private ComboBox<Building> bldgDropDown = new ComboBox<Building>();
	final private String[] projector = { "Yes", "No" };
	private College college;
	final private ComboBox<String> projDropDown = new ComboBox<String>();
	private BackgroundFill back;
	private Background backGr;
	private Label bldgLbl;
	private Label roomNumLbl;
	private Label capLbl;
	private Label headerLbl;
	private Label projLbl;

	public ClassroomPane(College college) {
		this.college = college;
		classroomGrid = new GridPane();
		classroomGrid.setGridLinesVisible(false);
		classroomGrid.setAlignment(Pos.CENTER);
		makeBtns();
		makeTextFields();
		setUpBtnListeners();
		makeLabels();
		populateGrid();
		back = new BackgroundFill(Color.rgb(221, 221, 221, 0.65), CornerRadii.EMPTY, Insets.EMPTY);
		backGr = new Background(back);
		classroomGrid.setMaxSize(1000, 500);
		classroomGrid.setBackground(backGr);
		classroomGrid.setVgap(20);
		classroomGrid.setHgap(20);
		classroomGrid.setPadding(new Insets(30));
	}

	private void populateGrid() {
		classroomGrid.add(roomNumberField, 3, 3);
		classroomGrid.add(bldgDropDown, 3, 4);
		classroomGrid.add(projDropDown, 3, 5);
		classroomGrid.add(capacityField, 3, 6);
		classroomGrid.add(findBtn, 1, 10);
		classroomGrid.add(insertBtn, 3, 10);
		classroomGrid.add(updateBtn, 5, 10);
		classroomGrid.add(bldgLbl, 2, 4);
		classroomGrid.add(roomNumLbl, 2, 3);
		classroomGrid.add(capLbl, 2, 6);
		classroomGrid.add(headerLbl, 3, 1);
		classroomGrid.add(projLbl, 2, 5);
	}

	private void makeLabels() {
		headerLbl = new Label("Classroom View");
		headerLbl.setMinWidth(100);
		bldgLbl = new Label("Building:");
		roomNumLbl = new Label("Room Number:");
		capLbl = new Label("Capacity:");
		projLbl = new Label("Projector:");
		projLbl.setMinSize(60, 40);
		headerLbl.setAlignment(Pos.CENTER);
		headerLbl.setTextAlignment(TextAlignment.CENTER);
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
		bldgDropDown.getItems().addAll(Building.values());
		projDropDown.getItems().addAll(projector);
		bldgNameField = new TextField();
		roomNumberField = new TextField();
		capacityField = new TextField();
		bldgNameField.setMaxSize(200, 25);
		bldgNameField.setPromptText("Building Name");
		roomNumberField.setMaxSize(200, 25);
		roomNumberField.setPromptText("Room Number");
		capacityField.setMaxSize(200, 25);
		capacityField.setPromptText("Capacity");
	}

	private void setUpBtnListeners() {
		insertBtn.setOnAction((e) -> {
			insert();
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
		Classroom classroom = college.getClassroomBag().findByRoomNumber(roomNumberField.getText());
		if (classroom != null) {
			college.getClassroomBag().deleteByRoomNumber(roomNumberField.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Remove Listing");
			alert.setHeaderText("Classroom deletion.");
			alert.setContentText("Success.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("Room number not found.");
			alert.showAndWait();
		}
	}
	public void update(){
		Classroom classroom = college.getClassroomBag().findByRoomNumber(roomNumberField.getText());
		if (classroom != null) {
			try {
				Classroom c = ((Classroom) classroom);
				c.setCapacity(Integer.parseInt(capacityField.getText()));
				c.setRoomNumber(roomNumberField.getText());
				c.setProjector((String) projDropDown.getValue());
				c.setBuilding((Building) bldgDropDown.getValue());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Listing Update!");
				alert.setHeaderText("Classroom update.");
				alert.setContentText("Update complete.");
				alert.showAndWait();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (ValueException e1) {
				e1.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("Room number not found.");
			alert.showAndWait();
		}
	}
	public void insert(){
		if (college.getClassroomBag().findByRoomNumber(roomNumberField.getText()) == null) {
			Classroom classroom;
			try {
				classroom = new Classroom(roomNumberField.getText(), (Building) bldgDropDown.getValue(),
						((String) projDropDown.getValue()), (Integer.parseInt(capacityField.getText())));
				college.getClassroomBag().insert(classroom);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New Listing!");
				alert.setHeaderText("New Classroom addition.");
				alert.setContentText("Success.");
				alert.showAndWait();
			} catch (NumberFormatException | ValueException e1) {
				e1.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Duplicate Room error ");
			alert.setHeaderText("Classroom already exists");
			alert.setContentText("Clear the roomNumber field to create a new record.");
			alert.showAndWait();
		}
	}

	public void search() {
		Classroom classroom = college.getClassroomBag().findByRoomNumber(roomNumberField.getText());
		if (classroom != null) {
			Classroom c = ((Classroom) classroom);
			bldgDropDown.setValue(c.getBuilding());
			roomNumberField.setText(c.getRoomNumber());
			capacityField.setText("" + c.getCapacity());
			projDropDown.setValue(c.getProjector());
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("Room number not found.");
			alert.showAndWait();
		}
	}

	public GridPane getGrid() {
		return classroomGrid;
	}

	public TextField getRoomNumberField() {
		return roomNumberField;
	}

}