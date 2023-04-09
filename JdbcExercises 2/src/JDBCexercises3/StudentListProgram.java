package JDBCexercises3;

import java.util.List;

import data_access.StudentDAO;
import model.Student;

public class StudentListProgram {

	public static void main(String[] args) {
		System.out.println("=== Print students (DAO version) ===");

		StudentDAO dao = new StudentDAO();

		List<Student> studentList = dao.getAllStudents();

		if (studentList == null) {
			System.out.println("The database is temporarily unavailable. Please try again later.");
		} else {
			for (Student student : studentList) {
				//student.getId() + ": " + student.getFirstName() + " " + student.getLastName() 
				//+ ", " + student.getStreetAdress() + ", " + student.getPostCode() + " " + student.getPostOffice()
				System.out.println(student.toString());
			}
		}

	}

}
