package JDBCexercises2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String streetAddress = resultSet.getString("streetaddress");
                int postCode = resultSet.getInt("postcode");
                String postOffice = resultSet.getString("postoffice");

                System.out.println(id + ", " + firstName + " " + lastName + ", " + streetAddress + ", " + postCode + " " + postOffice);
            } else {
                System.out.println("Unknown student id (" + givenID + ")");
            }

        } catch (SQLException sqle) {
            System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

        } finally {
            DbUtils.closeQuietly(resultSet, preparedStatement, connection);
        }
    }
}
