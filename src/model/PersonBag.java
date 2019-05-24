package model;

import java.io.Serializable;
import java.util.Random;

public class PersonBag implements Serializable{
	private Person[] personArray;
	private int nElems;
	private Random random = new Random();

	public PersonBag(int maxSize) {
		super();
		this.personArray = new Person[maxSize];
		this.nElems = 0;
	}

	public Person[] getPersonArray() {
		return personArray;
	}

	public void insert(Person person) {
		personArray[nElems++] = person;
	}

	public void display() {
		for(int i=0; i<nElems; i++) {
		System.out.println(personArray[i]);
		}
	}

	public Person findById(String id) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (personArray[i].getId().equals(id)) {
				return personArray[i];
			}
		}
		return null;
	}

	public Person deleteById(String id) {
		int i;
		Person temp = null;
		for (i = 0; i < nElems; i++) {
			if (personArray[i].getId().equals(id)) {
				break;
			}
		}
		if (i == nElems) {
			System.out.println("ID not found.");
			return null;
		} else {
			temp = personArray[i];
			for (int j = i; j < nElems - 1; j++) {
				personArray[j] = personArray[j + 1];
			}
			nElems--;
		}
		return temp;
	}
	
	
	public Person emitPerson() {
		return personArray[random.nextInt(nElems)];
	}

}
