package JDBCexercises3;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentSearchProgram {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("=== Find student by id (DAO version) ===");
		System.out.print("Enter student id: ");
		int id = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
		Student student = dao.getStudentById(id);
		if (student != null) {
			System.out.print(student.toString());	
		} else {
			System.out.println("Unknown student id (" + id + ")");
		}
		
	}

}
