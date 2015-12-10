package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectDatabase {

	static Connection conn = null;
	static String conString = "jdbc:mysql://localhost:8888/library";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "Loitv";
	static String password = "xxxx";

	/**
	 * the constructor default
	 */
	public ConnectDatabase() {

	}

	/**
	 * the method create a connection to connect with databases
	 * 
	 * @return connection to connect databases
	 */
	public static Connection getConnection() {
		try {

			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(conString, userName, password);

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to database");
		}
		return conn;
	}
}