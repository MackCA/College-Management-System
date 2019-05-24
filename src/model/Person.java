package model;

import java.io.Serializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Person implements Serializable{

	private static final long serialVersionUID = 2386470227409417241L;
	private String firstName;
	private String lastName;
	private String id;
	private String phone;
	private Address address;
	private final int idLength=8;

	public Person(String firstName, String lastName,String phone,Address address)throws ValueException{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = utils.IdFactory.emitId(idLength);
		this.phone =phone;
		this.address =address;

		if(phone.replaceAll("[-]", "").length()!=10){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid phone number.");
			alert.setContentText("Please enter a 10 digit phone number.");
			alert.showAndWait();
			throw new ValueException();
		}

	}

	public String getFullName() {
		String fullName = firstName+" "+lastName;
		return fullName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws ValueException{
		if(phone.replaceAll("[-]", "").length()!=10){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid phone number.");
			alert.setContentText("Please enter a 10 digit phone number.");
			alert.showAndWait();
			throw new ValueException();
		}
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", phone=" + phone
				+ ", address=" + address + "]";
	}







}
