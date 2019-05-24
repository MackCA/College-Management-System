package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import model.TextbookBag;
import model.ValueException;

public class TextbookFactory {
	private static Random random = new Random();
	private static model.TextbookBag textbookBag;
	private static model.Textbook textbook;
	private static int textbookListLength = 1000;

	public TextbookFactory(model.TextbookBag textbookBag) {
		super();
		this.textbookBag = textbookBag;
	}

	public static model.Textbook importTextbook() throws FileNotFoundException, ValueException {
		PrintWriter output = new PrintWriter("data\\export\\Textbook List.txt");
		File isbnFile = new File("data\\import\\textbook_isbns.txt");
		File textTitleFile = new File("data\\import\\textbook_titles.txt");
		Scanner inputIsbns = new Scanner(isbnFile);
		Scanner inputTitles = new Scanner(textTitleFile);
		while (inputTitles.hasNextLine()) {
			for (int i = 0; i < textbookListLength; i++) {
				try {
					String textbookTitle = inputTitles.nextLine();
					String isbn = inputIsbns.nextLine();
					String author = generateAuthor();
					double textbookPrice = generateTextbookPrice();
					String description = null;
					model.Textbook textbook = new model.Textbook(textbookTitle, isbn, author, textbookPrice,
							description);
					output.println(textbook);
					textbookBag.insert(textbook);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		inputIsbns.close();
		inputTitles.close();
		output.close();
		return textbook;
	}

	public static String generateAuthor() throws FileNotFoundException {
		return NameFactory.emitLastName() + " " + NameFactory.emitFirstName();
	}

	public static double generateTextbookPrice() {
		return ((int) ((random.nextDouble() + random.nextInt(500)) * 100) / 100d);
	}

	public static TextbookBag getTextbookBag() {
		return textbookBag;
	}

}