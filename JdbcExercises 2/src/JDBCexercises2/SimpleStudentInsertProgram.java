package JDBCexercises2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class SimpleStudentInsertProgram {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementCheckID = null;
		ResultSet resultSet = null;

		System.out.println("=== Add student ===");

		int id = -1;

		try {
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			System.out.print("Id: ");
			id = Integer.parseInt(scanner.nextLine());

			String sqlCheckID = "SELECT id FROM Student WHERE id = ?";
			preparedStatementCheckID = connection.prepareStatement(sqlCheckID);
			preparedStatementCheckID.setInt(1, id);
			resultSet = preparedStatementCheckID.executeQuery();

			boolean idExists = false;

			while (resultSet.next()) {
				int idList = resultSet.getInt("id");
				if (id == idList) {
					idExists = true;
					break;
				}
			}

			if (idExists) {
				System.out.println("Cannot insert the student. The student id " + id + " is already in use.");
				scanner.close();
				DbUtils.closeQuietly(resultSet, preparedStatementCheckID, connection);
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
			
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
			  
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, id); preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName); preparedStatement.setString(4,
			street); preparedStatement.setString(5, postCode);
			preparedStatement.setString(6, postOffice);
			 
			preparedStatement.executeUpdate();
			 
			System.out.println(); System.out.println("Student data added!");
			

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println("Cannot insert the student. The student id " + id + " is already in use.");
				scanner.close();
			} else {
				System.out.println("The database is temporarily unavailable. Please try again later."
						+ "\n[ERROR] Database error. " + sqle.getMessage());
			}

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatementCheckID, connection);
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}	
	
	}
}
