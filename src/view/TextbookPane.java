package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.College;
import model.Textbook;
import model.ValueException;

public class TextbookPane {
	private GridPane textbookGrid;
	private TextField titleField;
	private TextField authorField;
	private TextField descriptionField;
	private TextField isbnField;
	private TextField priceField;
	private Button insertBtn;
	private Button removeBtn;
	private Button findBtn;
	private Button updateBtn;
	private College college;
	private BackgroundFill back;
	private Background backGr;
	private Label titleLbl;
	private Label authorLbl;
	private Label descrLbl;
	private Label isbnLbl;
	private Label priceLbl;
	private Label headerLbl;

	public TextbookPane(College college) {
		this.college = college;
		textbookGrid = new GridPane();
		back = new BackgroundFill(Color.rgb(221, 221, 221, 0.65), CornerRadii.EMPTY, Insets.EMPTY);
		backGr = new Background(back);
		textbookGrid.setMaxSize(1000, 500);
		textbookGrid.setBackground(backGr);
		textbookGrid.setGridLinesVisible(false);
		textbookGrid.setAlignment(Pos.CENTER);
		makeBtns();
		makeTextFields();
		setUpBtnListeners();
		makeLabels();
		populateGrid();
		textbookGrid.setVgap(20);
		textbookGrid.setHgap(20);
		textbookGrid.setPadding(new Insets(30));

	}

	private void populateGrid() {
		textbookGrid.add(titleField, 3, 3);
		textbookGrid.add(authorField, 3, 4);
		textbookGrid.add(priceField, 3, 5);
		textbookGrid.add(descriptionField, 3, 6);
		textbookGrid.add(isbnField, 3, 9);
		textbookGrid.add(findBtn, 1, 10);
		textbookGrid.add(insertBtn, 3, 10);
		textbookGrid.add(updateBtn, 5, 10);
		textbookGrid.add(titleLbl, 2, 3);
		textbookGrid.add(authorLbl, 2, 4);
		textbookGrid.add(priceLbl, 2, 5);
		textbookGrid.add(isbnLbl, 2, 9);
		textbookGrid.add(descrLbl, 2, 6);
		textbookGrid.add(headerLbl, 3, 1);
	}

	private void makeLabels() {
		headerLbl = new Label("Textbook View");
		headerLbl.setMinWidth(100);
		isbnLbl = new Label("ISBN:");
		isbnLbl.setMinWidth(60);
		authorLbl = new Label("Author:");
		priceLbl = new Label("Price:");
		descrLbl = new Label("Description:");
		descrLbl.setMinSize(60, 40);
		headerLbl.setAlignment(Pos.CENTER);
		headerLbl.setTextAlignment(TextAlignment.CENTER);
		titleLbl = new Label("Title:");
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
		titleField = new TextField();
		authorField = new TextField();
		isbnField = new TextField();
		priceField = new TextField();
		descriptionField = new TextField();
		titleField.setMinSize(300, 40);
		titleField.setPromptText("Title Name");
		authorField.setMinSize(300, 40);
		authorField.setPromptText("Author Name");
		isbnField.setMinSize(200, 40);
		isbnField.setPromptText("ISBN");
		priceField.setMinSize(200, 40);
		priceField.setPromptText("Price");
		descriptionField.setMinSize(300, 80);
		descriptionField.setPromptText("Description");
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

	public void update() {
		Textbook textbook = college.getTextbookBag().findByIsbn(isbnField.getText());
		if (textbook != null) {
			try {
				textbook.setBookTitle(titleField.getText());
				textbook.setAuthor(authorField.getText());
				textbook.setPrice(Double.parseDouble(priceField.getText().replaceAll("[$,]", "")));
				textbook.setDescription(descriptionField.getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Listing Update!");
				alert.setHeaderText("Textbook update.");
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
			alert.setContentText("ISBN not found.");
			alert.showAndWait();
		}
	}

	public void remove() {
		Textbook textbook = college.getTextbookBag().findByIsbn(isbnField.getText());
		if (textbook != null) {
			college.getTextbookBag().deleteByIsbn(isbnField.getText());
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
	}

	public void insert() {
		if (college.getTextbookBag().findByIsbn(isbnField.getText()) == null) {
			try {
				Textbook textbook = new Textbook(titleField.getText(), isbnField.getText(), authorField.getText(),
						Double.parseDouble(priceField.getText().replaceAll("[$,]", "")), descriptionField.getText());
				college.getTextbookBag().insert(textbook);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New Listing!");
				alert.setHeaderText("New Textbook addition.");
				alert.setContentText("Success.");
				alert.showAndWait();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (ValueException e1) {
				e1.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Duplicate ISBN error ");
			alert.setHeaderText("Textbook already exists");
			alert.setContentText("Clear the ISBN field to create a new record.");
			alert.showAndWait();
		}
	}

	public void search() {
		Textbook textbook = college.getTextbookBag().findByIsbn(isbnField.getText());
		if (textbook != null) {
			titleField.setText(textbook.getBookTitle());
			authorField.setText(textbook.getAuthor());
			priceField.setText(textbook.getPrice());
			descriptionField.setText("" + textbook.getDescription());
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Not found.");
			alert.setContentText("ISBN not found.");
			alert.showAndWait();
		}
	}

	public GridPane getGrid() {
		return textbookGrid;
	}

	public TextField getIsbnField() {
		return isbnField;
	}

}
