package model;

import java.io.Serializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Classroom implements Serializable{
	private String roomNumber;
	private Building building;
	private String projector;
	private int capacity;

	public Classroom(String roomNumber, Building building, String projector, int capacity) throws ValueException{
		super();
		this.roomNumber = roomNumber;
		this.building = building;
		this.projector=projector;
		this.capacity =capacity;
		if(roomNumber.charAt(0) != String.valueOf(building).charAt(0)||roomNumber.length()!=4){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Room Number.");
			alert.setContentText("Room number must consist of the first letter of \nthe building name followed by a 3 digit value");
			alert.showAndWait();
			throw new ValueException();
		}
		for(int i=1; i<4; i++){
		if(!Character.isDigit(roomNumber.charAt(i))){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Room Number.");
			alert.setContentText("Room number must consist of the first letter of \nthe building name followed by a 3 digit number");
			alert.showAndWait();
			throw new ValueException();
		}
		}
		if(capacity<0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Room Capacity.");
			alert.setContentText("Value must be greater than 0.");
			alert.showAndWait();
			throw new ValueException();
		}
	}
	public void setRoomNumber(String roomNumber) throws ValueException{
		if(roomNumber.charAt(0) != String.valueOf(building).charAt(0)||roomNumber.length()!=4){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Room Number.");
			alert.setContentText("Room number must consist of the first letter of \nthe building name followed by a 3 digit value");
			alert.showAndWait();
			throw new ValueException();
		}
		this.roomNumber = roomNumber;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public String getProjector() {
		return projector;
	}
	public void setProjector(String projector) {
		this.projector = projector;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) throws ValueException{
		if(capacity<0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid Room Capacity.");
			alert.setContentText("Value must be greater than 0.");
			alert.showAndWait();
			throw new ValueException();
		}
		this.capacity = capacity;
	}

	public String getRoomNumber() {
		return roomNumber;
	}
	public Building getBuilding() {
		return building;
	}
	@Override
	public String toString() {
		return "Classroom [roomNumber=" + roomNumber + ", building=" + building + ", projector=" + projector
				+ ", capacity=" + capacity + "]";
	}



}