package JDBCexercises3;

import data_access.StudentDAO;

public class StudentJSONProgram {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		System.out.println(dao.getAllStudentsJSON());

	}

}
