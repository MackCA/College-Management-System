package model;

import java.io.Serializable;
import java.util.Random;

public class TextbookBag implements Serializable{
	private Textbook[] textbookArray;
	private int nElems;
	private Random random = new Random();

	public Textbook[] getTextbookArray() {
		return textbookArray;
	}

	public int getnElems() {
		return nElems;
	}

	public TextbookBag(int maxSize) {
		super();

		this.textbookArray = new Textbook[maxSize];
		this.nElems = 0;
	}

	public void insert(Textbook textbook) {
		textbookArray[nElems++] = textbook;
	}

	public void display() {
		for(int i=0; i<nElems; i++) {
		System.out.println(textbookArray[i]);
		}
	}

	public Textbook deleteByIsbn(String isbn) {
		int i = 0;
		Textbook temp;
		for (i = 0; i < nElems; i++) {
			if (textbookArray[i].getIsbn().equals(isbn)) {
				break;
			}
		}

		if (i == nElems) {
			System.out.println("ISBN not found.");
			return null;
		} else {
			temp = textbookArray[i];
			for (int j = i; j < nElems - 1; j++) {
				textbookArray[j] = textbookArray[j + 1];
			}
			nElems--;
			return temp;
		}

	}

	public Textbook findByIsbn(String isbn) {
		int i = 0;
		for (i = 0; i < nElems; i++) {
			if (textbookArray[i].getIsbn().equals(isbn)) {
				return textbookArray[i];
			}
		}
		return null;
	}

	public Textbook emitTextbook() {
		return textbookArray[random.nextInt(nElems)];
	}
}
