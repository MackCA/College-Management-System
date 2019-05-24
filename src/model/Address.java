package model;

import java.io.Serializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Address implements Serializable{
	private String streetAddress;
	private String city;
	private States state;
	private String zip;

	public Address(String streetAddress, String city, States state, String zip)throws ValueException{
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		if (zip.length()!=5) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Zip code.");
			alert.setContentText("Zip must be 5 digits in length.");
			alert.showAndWait();
			throw new ValueException();
		}
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) throws ValueException{
		if (zip.length()!=5) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Zip code.");
			alert.setContentText("Zip must be 5 digits in length.");
			alert.showAndWait();
			throw new ValueException();
		}
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address: Street Address:" + streetAddress + ", City:" + city
				+ "State:" + state + "Zip:" + zip;
	}

}
