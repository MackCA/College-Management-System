package model;

import java.io.Serializable;
import java.text.NumberFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Textbook implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 8252951451429404666L;
	private String bookTitle;
	private String isbn;
	private String author;
	private double price;
	private String description;
	private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

	public Textbook(String bookTitle, String isbn, String author, double price, String description) throws ValueException{
		super();
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.description = description;
		if(price<0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid Price");
			alert.setContentText("Price cannot be negative.");
			alert.showAndWait();
			throw new ValueException();
		}
		if(isbn.replaceAll("[-]","").length()!=13 &&isbn.replaceAll("[-]","").length()!=10){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid ISBN Formatting. Format must match ISBN 10 or ISBN 13.");
			alert.setContentText("ISBN 10 has 10 characters. ISBN 13 has 13 characters.");
			alert.showAndWait();
			throw new ValueException();
		}
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) throws ValueException{
		if(isbn.replaceAll("[-]","").length()!=13 &&isbn.replaceAll("[-]","").length()!=10){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid ISBN Formatting. Format must match ISBN 10 or ISBN 13.");
			alert.setContentText("ISBN 10 has 10 characters. ISBN 13 has 13 characters.");
			alert.showAndWait();
			throw new ValueException();
		}
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return currencyFormatter.format(price);
	}
	public void setPrice(double price) throws ValueException{
		if(price<0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Price");
			alert.setContentText("Price cannot be negative.");
			alert.showAndWait();
			throw new ValueException();
		}
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Textbook: Title:" + bookTitle + "    ISBN: " + isbn + "    Author: " + author + "    Price:" + price
				+ "    Description:" + description;
	}





}
