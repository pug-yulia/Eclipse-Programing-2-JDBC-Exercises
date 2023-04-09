package JDBCexercises4;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentUpdateProgram {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		
		System.out.println("=== Update student ===");
		System.out.print("Id: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("First name: ");
		String firstName = scanner.nextLine();
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Street: ");
		String street = scanner.nextLine();
		System.out.print("Postcode: ");
		String postCode = scanner.nextLine();
		System.out.print("Post office: ");
		String postOffice = scanner.nextLine();

		scanner.close();
		
		Student student = new Student(id, firstName, lastName, street, postCode, postOffice);
        dao.updateStudent(student);

	}

}
