package app;

import config.BagConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.College;
import utils.Utils;
import view.BottomPane;
import view.MenuBarPane;

public class App extends Application {
	final int MAX_PERSONS = BagConfig.MAX_PERSONS;
	final int MAX_BOOKS = BagConfig.MAX_BOOKS;
	final int MAX_CLASSROOMS = BagConfig.MAX_CLASSROOMS;
	final int MAX_COURSES = BagConfig.MAX_COURSES;
	final int MAX_FACULTY_COURSES = BagConfig.MAX_FACULTY_COURSES;
	final int MAX_STUDENT_COURSES = BagConfig.MAX_STUDENT_COURSES;
	private College college;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		college = new College(MAX_PERSONS, MAX_BOOKS, MAX_CLASSROOMS, MAX_COURSES, MAX_FACULTY_COURSES,
				MAX_STUDENT_COURSES);
		Utils.restore(college);
		BorderPane root = new BorderPane();
		BottomPane bottomPane = new BottomPane(college, root);
		root.setBottom(bottomPane.getButtonBox());
		root.setTop(new MenuBarPane(college, root, bottomPane.getStudentView()).getMenuBar());
		Scene scene = new Scene(root, 1400, 900);
		scene.getStylesheets().add("stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("College Management System");
		primaryStage.show();
	}

}
