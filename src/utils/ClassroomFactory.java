package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import model.ValueException;

public class ClassroomFactory {
	static Random random = new Random();
	private static model.Building building;
	private static String roomNumber;
	private static  String classroom;
	private static String projector;
	private static int capacity;
	private static model.ClassroomBag classroomBag;

	public ClassroomFactory(model.ClassroomBag classroomBag) {
		this.classroomBag = classroomBag;
	}

	public static model.Classroom generateClassroom() throws IOException, ValueException {
		FileWriter fw = new FileWriter("data\\export\\Classroom List.txt",true);
		PrintWriter output = new PrintWriter(fw);
		boolean duplicate = true;
		do {
			building = model.Building.values()[random.nextInt(model.Building.values().length)];
			String buildingChar = "" + String.valueOf(building).charAt(0);
			roomNumber = String.valueOf(random.nextInt(300) + 101);
			classroom = buildingChar + roomNumber;
			capacity = random.nextInt(45)+30;
			while(capacity%5 !=0){
				capacity = random.nextInt(45)+30;
			}
			int roll = random.nextInt(2)+1;
			if(roll==1){
			projector ="Yes";
			}
			else{
				projector="No";
			}
			for (int i = 0; i < classroomBag.getnElems()+1; i++) {
				if (String.valueOf(classroomBag.getClassroomArray()[i]).equals(classroom)) {
					building = model.Building.values()[random.nextInt(model.Building.values().length)];
					buildingChar = "" + String.valueOf(building).charAt(0);
					roomNumber = String.valueOf(random.nextInt(300) + 101);
					classroom = buildingChar + roomNumber;
					capacity = random.nextInt(45)+30;
					while(capacity%5 !=0){
						capacity = random.nextInt(45)+30;
					}
					roll = random.nextInt(1);
					if(roll==1){
					projector ="Yes";
					}
					else{
						projector="No";
					}
				} else {
					duplicate = false;
					break;
				}
			}
		} while (duplicate);
		model.Classroom classroomObj = new model.Classroom(classroom, building, projector, capacity);
		classroomBag.insert(classroomObj);
		output.println(classroomObj);
		output.close();
		return classroomObj;
	}

}
