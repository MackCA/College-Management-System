package model;

import java.text.NumberFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Faculty extends Person {
	private Title title;
	private double salary;
	private Department department;
	private MiniFacultyCourseBag miniFacultyCourseBag;
	private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();


	public Faculty(String firstName, String lastName, String phone, Address address, Title title, double salary,
			Department department, MiniFacultyCourseBag miniFacultyCourseBag) throws ValueException{
		super(firstName, lastName, phone, address);
		this.title = title;
		this.salary = salary;
		this.department = department;
		this.miniFacultyCourseBag = miniFacultyCourseBag;
		if(salary<0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Salary");
			alert.setContentText("Salary cannot be negative.");
			alert.showAndWait();
			throw new ValueException();
		}
	}

	public Title getTitle() {
		return title;
	}

	public String getSalary() {
		return currencyFormatter.format(salary);
	}

	public Department getDepartment() {
		return department;
	}

	public MiniFacultyCourseBag getMiniFacultyCourseBag() {
		return miniFacultyCourseBag;
	}

	public MiniFacultyCourseInfo[] getCoursesTeaching() {
		return miniFacultyCourseBag.findCoursesTeaching();
	}

	public MiniFacultyCourseInfo[] getCoursesTaught() {
		return miniFacultyCourseBag.findCoursesTaught();
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public void setSalary(double salary) throws ValueException{
		if(salary<0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Salary");
			alert.setContentText("Salary cannot be negative.");
			alert.showAndWait();
			throw new ValueException();
		}
		this.salary = salary;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Faculty " + super.toString()+"title:" + title + ", salary=" + salary + ", department=" + department
				+ ", miniFacultyCourseBag=" + miniFacultyCourseBag ;
	}






}