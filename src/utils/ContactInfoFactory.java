package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import model.Address;
import model.States;
import model.ValueException;

public class ContactInfoFactory {
	private static int CIMaxSize = 2000;
	private static String[] cityArray = new String[CIMaxSize];
	private static String[] streetArray= new String[CIMaxSize];
	private static String[] suffixArray= new String[CIMaxSize];
	private static int citynElems=0;
	private static int streetnElems=0;
	private static int suffixnElems=0;
	private static String buildingNumber;
	private static String street;
	private static States state;
	private static String city;
	private static String zip;


	static Random random = new Random();

	public static String emitPhone() {
		String Phone = "631-";

		for (int i = 0; i < 3; i++) {
			Phone += String.valueOf(random.nextInt(10));
		}
		Phone += "-";
		for (int i = 0; i < 4; i++) {
			Phone += String.valueOf(random.nextInt(10));
		}
		return Phone;
	}

	public static void createStreetAddressArrays() {
		File cities = new File("data\\import\\cities.txt");
		File streets = new File("data\\import\\new-york.osm.streets.txt");
		File suffixes = new File("data\\import\\suffixes.txt");
		Scanner inCit;
		Scanner inStr;
		Scanner inSuf;
		try {
			inCit = new Scanner(cities);
			inStr = new Scanner(streets);
			inSuf = new Scanner(suffixes);

			while (inCit.hasNextLine()&& citynElems<2000) {
				cityArray[citynElems++] = inCit.nextLine();
			}
			while(inStr.hasNext()&& streetnElems<2000){
				streetArray[streetnElems++] = inStr.nextLine();
			}
			while(inSuf.hasNext()&& suffixnElems<2000){
				suffixArray[suffixnElems++] = inSuf.nextLine();
			}
			inCit.close();
			inStr.close();
			inSuf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

	public static Address emitAddress() throws ValueException{
		createStreetAddressArrays();
		buildingNumber = String.valueOf(random.nextInt(3000)+1);
		street =""+buildingNumber+" "+streetArray[random.nextInt(streetnElems)]+" "+suffixArray[random.nextInt(suffixnElems)];
		state= model.States.values()[random.nextInt(model.States.values().length)];
		city=cityArray[random.nextInt(citynElems)];
		zip= String.valueOf(random.nextInt(99950)+11001);
		if(zip.length()!=5){
			while(zip.length()!=5){
			zip= String.valueOf(random.nextInt(99950)+11001);
			}
		}
		Address address = new Address(street,city,state,zip);
		return address;
	}

}
