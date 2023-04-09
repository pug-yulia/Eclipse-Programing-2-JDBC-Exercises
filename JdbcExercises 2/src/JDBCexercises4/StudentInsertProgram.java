package JDBCexercises4;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentInsertProgram {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		
		System.out.println("=== Add student ===");
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
        int errorCode = dao.insertStudent(student);

        if (errorCode == 0) {
            System.out.println();
            System.out.println("Student data added!");
        } else if (errorCode == 1) {
        	 System.out.println();
            System.out.println("Cannot insert the student. The student id " + id + " is already in use.");
        } else {
        	System.out.println("The database is temporarily unavailable. Please try again later");
        }

	}

}
