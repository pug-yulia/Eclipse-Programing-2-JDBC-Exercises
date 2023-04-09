package JDBCexercises2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;
import data_access.StudentDAO;
import model.Student;

public class SimpleStudentSearchProgram {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		
		Connection connection = null;	
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
	
		System.out.println("=== Find student by id ===");

		try {
			System.out.print("Enter student id: ");
			int givenID = Integer.parseInt(scanner.nextLine());
			scanner.close();
		
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setInt(1, givenID);

			resultSet = preparedStatement.executeQuery();
			
			List<Student> result = dao.getStudentByID(givenID);
			
			if (result.size() <= 0) {
                System.out.println("Unknown student id (" + givenID + ")");
                return;
                
            } else {
            	 System.out.println(result.get(0));     
            }
			
//			boolean rowFound = false;
//
//			while (resultSet.next()) {
//				rowFound = true;
//
//				int id = resultSet.getInt("id");
//				String firstName = resultSet.getString("firstname");
//				String lastName = resultSet.getString("lastname");
//				String streetAddress = resultSet.getString("streetaddress");
//				int postCode = resultSet.getInt("postcode");
//				String postOffice = resultSet.getString("postoffice");
//
//				System.out.println(id + ", " + firstName +  " " + lastName + ", " + streetAddress + ", " + postCode + " " + postOffice);
//			} 
//
//			if (rowFound == false) {
//				System.out.println("Unknown student id (" + givenID + ")");
//			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

	}

}
