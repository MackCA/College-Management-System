package utils;

public class IdFactory {
	private static int idCounter = 1;

	public static String emitId(int idLength) {
		String rawId=String.valueOf(idCounter++);
		String buffer="";
		for (int i = rawId.length(); i < idLength; i++) {
			buffer = "0" + buffer;
		}
		return buffer+rawId;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		IdFactory.idCounter = idCounter;
	}
	
	

}
