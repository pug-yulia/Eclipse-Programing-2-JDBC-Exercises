package JDBCexercises2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;
import data_access.StudentDAO;
import model.Student;

// So i didn't get if i was supposed to hard code this or no, because InsertMovie example
// does not use MovieDAO, so the SimpleStudentInsertProgram is hard coded
// and this one uses StudentDAO

public class InsertStudentVerTwo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();

		Connection connection = null;

		System.out.println("=== Add student ===");

		int id = -1;

		try {
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			System.out.print("Id: ");
			id = Integer.parseInt(scanner.nextLine());

			if (dao.getStudentByID(id).size() > 0) {
                System.out.println("Cannot insert the student. The student id " + id + " is already in use.");
                scanner.close();
                return;
            }


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
                System.out.println("Cannot insert the student. The student id " + id + " is already in use.");
            }
			

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println("Cannot insert the student. The student id " + id + " is already in use.");
				scanner.close();
			} else {
				System.out.println("The database is temporarily unavailable. Please try again later."
						+ "\n[ERROR] Database error. " + sqle.getMessage());
			}

		} finally {
			DbUtils.closeQuietly(connection);
		}	
	
	}
	

	
}
