package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import view.SignUpView;

public class SignUpController {
	private SignUpView signUp;
	private boolean signUpStatus = true;

	// Constructor
	public SignUpController() {

		signUp = new SignUpView();

		// handle events for button Sign Up
		signUp.setBtnSignUpActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				signUp();
			}
		});

		// handle event for Password field
		signUp.setTfRePassWordAL(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				signUp();
			}
		});
	}

	public void signUp() {
		String inputID = signUp.getUserName();
		String inputPw1 = signUp.getPassword();
		String inputPw2 = signUp.getRePassword();
		String query = "select * from account";

		if (!inputPw1.equals(inputPw2)) {
			JOptionPane.showMessageDialog(null, "YOUR PASSWORDS NOT MATCH");
		} else {
			try {
				Statement statement = controll.ConnectDatabase.getConnection().createStatement();
				ResultSet rs = statement.executeQuery(query);
				String user;

				if (!rs.first()) {
					signUp.closeForm();
					JOptionPane.showMessageDialog(null, "SIGN UP SUCCESFULLY!");
					String query1 = "insert into `account` values (?,?,?);";
					String query2 = "insert into `personalInfo` values (?,?,?,?,?,?,?,?);";
					try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query1)) {
						insertStmt.setString(1, inputID);
						insertStmt.setString(2, inputPw1);
						insertStmt.setInt(3, 3);
						insertStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
					try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
							.prepareStatement(query2)) {
						insertStmt.setString(1, inputID);
						insertStmt.setString(2, null);
						insertStmt.setString(3, null);
						insertStmt.setInt(4, 0);
						insertStmt.setString(5, null);
						insertStmt.setString(6, null);
						insertStmt.setString(7, null);
						insertStmt.setInt(8, 3);
						insertStmt.executeUpdate();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				} else {
					do {
						user = rs.getString("userName");
						// password = rs.getString("password");

						signUpStatus = true;
						if (user.equalsIgnoreCase(inputID)) {
							String displayContent = "ACCOUNT ".concat(inputID.toUpperCase())
									.concat(" HAS BEEN EXISTED");
							JOptionPane.showMessageDialog(null, displayContent);
							signUpStatus = false;
							break;
						}
					} while (rs.next());
					if (signUpStatus) {
						signUp.closeForm();
						JOptionPane.showMessageDialog(null, "SIGN UP SUCCESFULLY!");
						String query1 = "insert into `account` values (?,?,?);";
						String query2 = "insert into `personalInfo` values (?,?,?,?,?,?,?,?);";
						try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query1)) {
							insertStmt.setString(1, inputID);
							insertStmt.setString(2, inputPw1);
							insertStmt.setInt(3, 3);
							insertStmt.executeUpdate();
						} catch (SQLException sqlEx) {
							sqlEx.printStackTrace();
						}
						try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query2)) {
							insertStmt.setString(1, inputID);
							insertStmt.setString(2, null);
							insertStmt.setString(3, null);
							insertStmt.setInt(4, 0);
							insertStmt.setString(5, null);
							insertStmt.setString(6, null);
							insertStmt.setString(7, null);
							insertStmt.setInt(8, 3);
							insertStmt.executeUpdate();
						} catch (SQLException sqlEx) {
							sqlEx.printStackTrace();
						}
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new SignUpController();
	}
}
