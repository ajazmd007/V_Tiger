package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBase_Utility {

	Connection con;

	public Connection GetDataBaseConnection(String url, String username, String password) {
		Driver dobj = null;
		try {
			dobj = new Driver();

			DriverManager.registerDriver(dobj);

			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

			System.out.println("Databse connection has not done");
		}
		return con;
	}

	public Connection getDataBaseConnection() {
		Driver dobj = null;
		try {
			dobj = new Driver();

			DriverManager.registerDriver(dobj);

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "123456");
		} catch (Exception e) {

			System.out.println("Databse connection has not done");
		}
		return con;
	}
	public void closeDataBaseConnection() {

		try {
			con.close();
		} catch (Exception e) {
			System.out.println("Database connection hasn't closed");
		}
	}

	public ResultSet executeSelectQuery(String query) {
		ResultSet result = null;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);

		} catch (Exception e) {

			System.out.println("Select query not executed");
		}

		return result;
	}

	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stat = con.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("NonSelect query not executed");
		}
		return result;

	}

}
