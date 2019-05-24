package model;

import java.util.Random;

public enum LetterGrade {
	
	A, B_PLUS, B, C_PLUS, C, D_PLUS, D, F,PENDING;
	
	public static model.LetterGrade emitLetterGrades() {
		Random random = new Random();
		return model.LetterGrade.values()[random.nextInt(model.LetterGrade.values().length)];
	}
}
