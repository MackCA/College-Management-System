package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class NameFactory {
	private static int nameArraySize = 2000;
	private static String firstName;
	private static String lastName;
	private static String[] firstNameArray = new String[nameArraySize];
	private static String[] boysNames = new String[nameArraySize];
	private static String[] girlsNames= new String[nameArraySize];
	private static String[] lastNameArray = new String[nameArraySize];
	private static String boyName;
	private static String girlName;
	private static int nElemsB=0;
	private static int nElemsG=0;
	private static Random random = new Random();

	public static void createFirstNameArray() throws FileNotFoundException {
		File firstNameFileBoy = new File("data\\import\\boys_names.txt");
		File firstNameFileGirl = new File("data\\import\\girls_names.txt");
		Scanner inputB = new Scanner(firstNameFileBoy);
		Scanner inputG = new Scanner(firstNameFileGirl);
		inputB.next();
		while (inputB.hasNextLine()) {
				 try {
					boyName = inputB.next();
				} catch (Exception e) {
					break;
				
				}
				boysNames[nElemsB++] = boyName.trim().replaceAll("[0-9.	]", "");
		}
		while (inputG.hasNextLine()) {
				 try {
					girlName = inputG.next();
				} catch (Exception e) {
					break;
				
				}
				 girlsNames[nElemsG++] = girlName.replaceAll("[	]", "").trim();
		}
		for(int i=0; i<nameArraySize; i++) {
			if (i%2==0) {
				firstNameArray[i]=boysNames[random.nextInt(nElemsB)];
			}
			else {
				firstNameArray[i]=girlsNames[random.nextInt(nElemsG)];
			}
		}
		inputB.close();
		inputG.close();
	}

	public static void createLastNameArray() throws FileNotFoundException {
		File lastNameFile = new File("data\\import\\Last Names.txt");
		Scanner input = new Scanner(lastNameFile);
		for (int i = 0; i < nameArraySize; i++) {
			try {
				lastNameArray[i] = input.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		input.close();
	}

	public static void generateNameArrays() throws FileNotFoundException {
		utils.NameFactory.createFirstNameArray();
		utils.NameFactory.createLastNameArray();
	}

	public static String emitFirstName() throws FileNotFoundException {
		Random random = new Random();
		firstName = firstNameArray[random.nextInt(2000)];
		return firstName;
	}

	public static String emitLastName() throws FileNotFoundException {
		Random random = new Random();
		lastName = lastNameArray[random.nextInt(2000)];
		return lastName;
	}

}
