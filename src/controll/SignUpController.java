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
		signUp.setBtnSignUpActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
					JOptionPane.showMessageDialog(null, "NONE ACCOUNT HAS BEEN CREATED");
				} else {
					do {
						user = rs.getString("userName");
						// password = rs.getString("password");

						signUpStatus = true;
						if (user.equals(inputID)) {
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
						String query1 = "insert into `account` values (?,?);";
						try (PreparedStatement insertStmt = (PreparedStatement) controll.ConnectDatabase.getConnection()
								.prepareStatement(query1)) {
							insertStmt.setString(1, inputID);
							insertStmt.setString(2, inputPw1);
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
