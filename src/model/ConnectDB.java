package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectDB {

	static Connection conn = null;
	static String conString = "jdbc:mysql://localhost:8888/qls";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String password = "xxxx";

	public ConnectDB() {

	}

	public static Connection getConnection() {
		try {

			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(conString, userName, password);

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Khong the ket noi CSDL");
		}
		return conn;
	}
}
