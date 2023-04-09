package JDBCexercises4;

import java.util.Scanner;

import data_access.StudentDAO;

public class StudentDeleteProgram {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		System.out.println("=== Delete student ===");

		System.out.print("Enter student id: ");
		int id = Integer.parseInt(scanner.nextLine());
		scanner.close();

		dao.deleteStudentById(id);
// 		=or=
//		int errorCode = dao.deleteStudent(id);
//		if (errorCode == 0) {
//			System.out.println("Student deleted!");
//		} else if (errorCode == 1) {
//			System.out.println("Nothing deleted. Unknown student id (" + id + ")");
//		} else {
//			System.out.println("An error occurred. Please try again later.");
//		}

	}

}
