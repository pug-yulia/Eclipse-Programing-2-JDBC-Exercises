package JDBCexercises2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

// Won't create deleteStudent method in StudentDAO since this is already short enough

public class SimpleStudentDeleteProgram {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		System.out.println("=== Delete student ===");
		
		int id = -1;
		
		try {
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);
			
			System.out.print("Enter student id: ");
			id = Integer.parseInt(scanner.nextLine());
			scanner.close();

			String sqlText = "DELETE FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, id);

			if (preparedStatement.executeUpdate() == 1) {
				System.out.println("Student deleted!");
			} else {
				System.out.println("Nothing deleted. Unknown student id (" + id + ")");
			}
			
			
		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println("Nothing deleted. Unknown student id (" + id + ")");
				scanner.close();
			} else {
				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			}

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

	}
	

}
