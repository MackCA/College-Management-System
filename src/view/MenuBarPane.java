package view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.Classroom;
import model.College;
import model.Faculty;
import model.Person;
import model.Student;
import model.Textbook;
import utils.Utils;

public class MenuBarPane {
	private MenuBar menuBar;
	private College college;
	private Menu fileMenu;
	private Menu studentMenu;
	private Menu facultyMenu;
	private Menu textbookMenu;
	private Menu classroomMenu;
	private StudentPane studentView;
	private BorderPane root;

	public MenuBarPane(College college, BorderPane root, StudentPane studentView) {
		this.college = college;
		this.root = root;
		this.menuBar = new MenuBar();
		this.studentView = studentView;
		buildFileMenu();
		buildStudentMenu();
		buildFacultyMenu();
		buildTextbookMenu();
		buildClassroomMenu();
		menuBar.getMenus().addAll(fileMenu, studentMenu, facultyMenu, textbookMenu, classroomMenu);
	}

	private void buildFileMenu() {
		fileMenu = new Menu("File");

		MenuItem restore = new MenuItem("Restore");
		restore.setOnAction(ex -> {
			Utils.restore(college);
		});

		MenuItem save = new MenuItem("Save");
		save.setOnAction(e -> {
			Utils.backup(college.getClassroomBag(), college.getCourseBag(), college.getTextbookBag(),
					college.getPersonBag(), college);
		});

		MenuItem exit = new MenuItem("Exit Program");
		exit.setOnAction(e -> {
			Utils.backup(college.getClassroomBag(), college.getCourseBag(), college.getTextbookBag(),
					college.getPersonBag(), college);
			Platform.exit();
		});
		fileMenu.getItems().addAll(save, restore, exit);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	private void buildStudentMenu() {
		studentMenu = new Menu("Student");
		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction((e) -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Delete");
			dialog.setContentText("Please enter a student ID:");
			dialog.setHeaderText("Delete student:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				Person person = college.getPersonBag().findById(idNumber);
				if (person instanceof Student) {
					college.getPersonBag().deleteById(idNumber);
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

			});

		});

		MenuItem search = new MenuItem("Search");
		search.setOnAction(e1 -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Please enter a student ID:");
			dialog.setHeaderText("Find student:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				StudentPane studentView = new StudentPane(college);
				root.setCenter(studentView.getGrid());
				studentView.getIdField().setText(idNumber);
				studentView.search();
			});
		});
		
		
		studentMenu.getItems().addAll(remove, search);
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	private void buildFacultyMenu() {
		facultyMenu = new Menu("Faculty");
		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction((e) -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Delete");
			dialog.setContentText("Please enter a faculty ID:");
			dialog.setHeaderText("Delete faculty member:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				Person person = college.getPersonBag().findById(idNumber);
				if (person instanceof Faculty) {
					college.getPersonBag().deleteById(idNumber);
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
			});
		});

		MenuItem search = new MenuItem("Search");
		search.setOnAction(e1 -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Please enter a faculty ID:");
			dialog.setHeaderText("Find faculty:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				FacultyPane facultyView = new FacultyPane(college);
				root.setCenter(facultyView.getGrid());
				facultyView.getIdField().setText(idNumber);
				facultyView.search();
			});
		});

		facultyMenu.getItems().addAll(remove, search);
	}

	///////////////////////////////////////////////////////////////////////////////////////
	private void buildTextbookMenu() {
		textbookMenu = new Menu("Textbook");
		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction((e) -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Delete");
			dialog.setHeaderText("Delete textbook:");
			dialog.setContentText("Please enter a textbook ISBN:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				Textbook textbook = college.getTextbookBag().findByIsbn(result.get());
				if (textbook != null) {
					college.getTextbookBag().deleteByIsbn(result.get());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Remove Listing");
					alert.setHeaderText("Textbook deletion.");
					alert.setContentText("Success.");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");
					alert.setHeaderText("Not found.");
					alert.setContentText("ISBN not found.");
					alert.showAndWait();
				}
			});
		});
		
		MenuItem search = new MenuItem("Search");
		search.setOnAction(e1 -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Please enter a textbook ISBN:");
			dialog.setHeaderText("Find textbook:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				TextbookPane textbookView = new TextbookPane(college);
				root.setCenter(textbookView.getGrid());
				textbookView.getIsbnField().setText(idNumber);
				textbookView.search();
			});
		});
		textbookMenu.getItems().addAll(remove,search);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	private void buildClassroomMenu() {
		classroomMenu = new Menu("Classroom");
		MenuItem remove = new MenuItem("Remove");
		remove.setOnAction((e) -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Delete");
			dialog.setHeaderText("Delete classroom:");
			dialog.setContentText("Please enter a classroom number:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				Classroom classroom = college.getClassroomBag().findByRoomNumber(result.get());
				if (classroom != null) {
					college.getClassroomBag().deleteByRoomNumber(result.get());
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
			});
		});
		
		MenuItem search = new MenuItem("Search");
		search.setOnAction(e1 -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search");
			dialog.setContentText("Please enter a classroom ID:");
			dialog.setHeaderText("Find classroom:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(idNumber -> {
				ClassroomPane classroomView = new ClassroomPane(college);
				root.setCenter(classroomView.getGrid());
				classroomView.getRoomNumberField().setText(idNumber);
				classroomView.search();
			});
		});
		classroomMenu.getItems().addAll(remove,search);
	}

	public Node getMenuBar() {
		return menuBar;
	}

}
